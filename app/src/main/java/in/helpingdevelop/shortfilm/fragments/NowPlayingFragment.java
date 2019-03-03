package in.helpingdevelop.shortfilm.fragments;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.programmer.realapp.FullscreenActivity;
import com.halilibo.bettervideoplayer.BetterVideoCallback;
import com.halilibo.bettervideoplayer.BetterVideoPlayer;

import in.helpingdevelop.shortfilm.R;

public class NowPlayingFragment extends Fragment implements BetterVideoCallback {
    View mView;
    private BetterVideoPlayer player;
    //    private static final String TEST_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    private String movieUrl = "https://shopkingapp.info/images/teaser.mp4";

    public static NowPlayingFragment newInstance(String movieUrl) {

        Bundle args = new Bundle();

        NowPlayingFragment fragment = new NowPlayingFragment();
        args.putString("movieUrl", movieUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        mView = inflater.inflate(R.layout.fragment_now_playing, container, false);
        // Inflate the layout for this fragment
        getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setupVideoPlayer();
        return mView;
    }

    public void setupVideoPlayer() {
        ImageView fullscreenview = (ImageView) mView.findViewById(R.id.full_screen);
        // Grabs a reference to the player view
        player = mView.findViewById(R.id.player);
        // Sets the callback to this Activity, since it inherits EasyVideoCallback
        player.setCallback(this);
        // Sets the source to the HTTP URL held in the TEST_URL variable.
        // To play files, you can use Uri.fromFile(new File("..."))
        movieUrl = getArguments().getString("movieUrl");
        player.setSource(Uri.parse(movieUrl));
        player.showControls();
        player.enableSwipeGestures();
        player.enableSwipeGestures(getActivity().getWindow());
        fullscreenview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FullscreenActivity.class);
                i.putExtra("current_position", player.getCurrentPosition());
                player.pause();
                startActivity(i);
            }
        });
        // From here, the player view will show a progress indicator until the player is prepared.
        // Once it's prepared, the progress indicator goes away and the controls become enabled for the user to begin playback.
        //BetterVideoPlayer player = // ...  // Sets a video source to be played. player.setSource(Uri);
        // Sets a callback to receive normal player events. player.setCallback(EasyVideoCallback);
        // Sets a callback that can be used to retrieve updates of the current playback position. player.setProgressCallback(EasyVideoProgressCallback);
        // Starts or resumes playback. player.start();
        // Seeks to a position in the video. player.seekTo(int);
        // Pauses playback. player.pause();
        // Stops playback. player.stop();
        // Resets the player, allowing a new source to be set. player.reset();
        // Releases the underlying MediaPlayer and cleans up resources. player.release();
        // Shows the default controls. They can be hidden again if the user taps the player. player.showControls();
        // Hides the default controls. They can be shown again if the user taps the player. player.hideControls().  // Shows the controls if they're hidden, hides them if they're shown. player.toggleControls();
        // Enables double tap to seek like in Youtube. Input: seek time in MS player.enableDoubleTapSeek(int);
        // Returns true if the default controls are currently shown. player.isControlsShown();
        // Hide the default controls and prevents them from being shown. player.disableControls();
        // Undoes disableControls() player.enableControls();
        // Returns true if the player has prepared for playback entirely player.isPrepared();
        // Returns true if the player is NOT paused. player.isPlaying();
        // Returns the current position of playback. player.getCurrentPosition();
        // Returns the total duration of the video. player.getDuration();
    }

    @Override
    public void onStarted(BetterVideoPlayer player) {
    }

    @Override
    public void onPaused(BetterVideoPlayer player) {
        super.onPause();
        player.pause();
    }

    @Override
    public void onPreparing(BetterVideoPlayer player) {

    }

    @Override
    public void onPrepared(BetterVideoPlayer player) {

    }

    @Override
    public void onBuffering(int percent) {

    }

    @Override
    public void onError(BetterVideoPlayer player, Exception e) {

    }

    @Override
    public void onCompletion(BetterVideoPlayer player) {

    }

    @Override
    public void onToggleControls(BetterVideoPlayer player, boolean isShowing) {

    }

    @Override
    public void onPause() {
        super.onPause();
        player.pause();
    }
}
