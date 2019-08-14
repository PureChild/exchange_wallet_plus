package dao.sqls;

public class Sqls {
    /**
     * 환전 예약 관련
     */
    public static final String SELECT_RESERVATION_INFOS_BEFORE_CONFIRM = "SELECT num, applicant, price, departure_date FROM reservation_info WHERE progress = 0";
    public static final String SELECT_RESERVATION_INFOS_BY_ID = "SELECT num, nation_code, progress, departure_date FROM reservation_info WHERE applicant = :userId ORDER BY num DESC";
    public static final String SELECT_RESERVATION_INFO_BY_NUM = "SELECT num, nation_code, price FROM reservation_info WHERE num = :num";

    public static final String UPDATE_RESERVATION_INFO = "UPDATE reservation_info SET nation_code = :nationCode, price = :price WHERE num = :num";
    public static final String UPDATE_RESERVATION_PROGRESS = "UPDATE reservation_info SET progress = progress + 1 WHERE num = :num";

    public static final String DELETE_RESERVATION_INFO = "DELETE FROM reservation_info WHERE num = :num";

    public static final String INSERT_CONFIRM_EXCHANGE_INFO = "INSERT INTO confirmed_exchange_info(reservation_num, exchange_date, exchange_code) "
                                                                    + "VALUES(:reservationNum, :exchangeDate, :exchangeCode) ";
    public static final String SELECT_RESERVATION_INFO_BY_CODE = "SELECT ri.num, ri.applicant, ri.nation_code, ri.price "
                                                                            + " FROM reservation_info AS ri "
                                                                            + " JOIN confirmed_exchange_info AS cei "
                                                                            + " ON ri.num = cei.reservation_num "
                                                                            + " AND cei.exchange_code = :exchangeCode "
                                                                            + " AND ri.progress = 1";

    public static final String SELECT_RESERVATION_INFO_BY_ID_AND_NATION_CODE = "SELECT num, nation_code, price FROM reservation_info WHERE applicant = :userId AND nation_code = :nationCode";

    public static final String INSERT_RESERVATION_INFO = "INSERT INTO reservation_info(applicant, price, departure_date, nation_code) VALUES(:userId, :price, :departureDate, :nationCode)";

    /**
     * 확정 예약 조회
     */
    public static final String SELECT_CONFIRMED_EXCHANGE_INFO = "SELECT exchange_date, exchange_code FROM confirmed_exchange_info WHERE reservation_num = :reservationNum";

    /**
     * 회원가입, 로그인 관련
     */
    public static final String INSERT_CUSTOMER = "INSERT INTO customer(id, pw, name, account) VALUES(:userId, :userPw, :userName, :userAccount)";
    public static final String SELECT_EXISTS_CUSTOMER = "SELECT EXISTS(SELECT * FROM customer WHERE id = :userId AND pw = :userPw)";

    /**
     * 목표 환율 설정 관련
     */
    public static final String SELECT_ALL_TARGET_RATES = "SELECT nation_code, id, rate FROM target_rate WHERE id = :userId";
    public static final String SELECT_TARGET_RATE = "SELECT id, nation_code, rate FROM target_rate WHERE id = :userId AND nation_code = :nationCode";

    public static final String INSERT_TARGET_RATE = "INSERT INTO target_rate(id, nation_code, rate) VALUES(:userId, :nationCode, :rate)";

    public static final String DELETE_TARGET_RATE = "DELETE FROM target_rate WHERE id = :userId AND nation_code = :nationCode";

    public static final String UPDATE_TARGET_RATE = "UPDATE target_rate SET nation_code = :nationCode, rate = :rate WHERE id = :userId AND nation_code = :originNationCode";
}
