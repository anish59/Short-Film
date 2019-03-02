package in.helpingdevelop.shortfilm.helper;

public class AppConstants {
    public static final String BASE_URL="http://13.126.215.205/filmywood/index.php/";

    public static final String LOGIN="Api/login"; /*Param : email,password*/
    public static final String REGISTER="Api/registration"; /*Param : email,password,name,ip*/
    public static final String UPDATE_PROFILE="Api/update_profile"; /* Param: name,mobile,dob,email,password,ticket_bookd*/
    public static final String NOW_RUNNING="Api/now_running"; /*Param : uid*/
    public static final String PREVIOUS="Api/previous"; /*Param : uid*/
    public static final String UPCOMING="Api/upcoming"; /*Param : uid*/
    public static final String BOOKED="Api/booked"; /*Param : uid,movie_id,order_id */

    // todo: doubt : does below API has multiple functionality (click book/trailer/yes/no)
    public static final String PLAYED="Api/booked"; /*Param : movie_id,uid*/

    public static final String CLICK_BOOK="Api/booked"; /*Param : movie_id*/

    public static final int SUCCESS = 1;
}
