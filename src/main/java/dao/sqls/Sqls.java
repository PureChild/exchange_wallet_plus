package dao.sqls;

public class Sqls {
    public static final String SELECT_RESERVATION_INFOS_BEFORE_CONFIRM = "SELECT num, applicant, price, departure_date FROM reservation_info WHERE progress = 0";
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
}
