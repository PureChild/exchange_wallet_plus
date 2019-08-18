package dao.sqls;

/**
 * 프로젝트 SQL 클래스
 * @author 이승수
 */
public class Sqls {
    /** 조회될 데이터 제한(시작 인덱스, 한 페이지 데이터 수) */
    public static final String LIMIT = " LIMIT :start, :dataPerPage";
    /** progress = 0인 예약 수 조회 */
    public static final String SELECT_TOTAL_COUNT_OF_RESERVATIONS_BEFORE_CONFIRM = "SELECT COUNT(*) FROM reservation_info WHERE progress = 0";
    /** 고객 ID를 통한 예약 수 조회 */
    public static final String SELECT_TOTAL_COUNT_OF_RESERVATIONS_BY_ID = "SELECT COUNT(*) FROM reservation_info WHERE applicant = :userId";
    /** 고객 ID를 통한 목표 환율 수 조회 */
    public static final String SELECT_TOTAL_COUNT_OF_TARGET_RATES = "SELECT COUNT(*) FROM target_rate WHERE id = :userId";
    /** progress = 0인 예약 조회 */
    public static final String SELECT_RESERVATION_INFOS_BEFORE_CONFIRM = "SELECT num, nation_code, applicant, price, departure_date FROM reservation_info WHERE progress = 0";
    /** 고객 ID를 통한 예약 조회 */
    public static final String SELECT_RESERVATION_INFOS_BY_ID = "SELECT num, nation_code, progress, departure_date FROM reservation_info WHERE applicant = :userId ORDER BY num DESC";
    /** 예약 번호를 통한 예약 조회 */
    public static final String SELECT_RESERVATION_INFO_BY_NUM = "SELECT num, nation_code, price, departure_date FROM reservation_info WHERE num = :num";
    /** 예약 정보 수정 */
    public static final String UPDATE_RESERVATION_INFO = "UPDATE reservation_info SET nation_code = :nationCode, price = :price WHERE num = :num";
    /** 예약 정보 progress 컬럼 수정 */
    public static final String UPDATE_RESERVATION_PROGRESS = "UPDATE reservation_info SET progress = progress + 1 WHERE num = :num";
    /** 예약 정보 삭제 */
    public static final String DELETE_RESERVATION_INFO = "DELETE FROM reservation_info WHERE num = :num";
    /** 환전일 확정 예약 추가 */
    public static final String INSERT_CONFIRM_EXCHANGE_INFO = "INSERT INTO confirmed_exchange_info(reservation_num, exchange_date, exchange_code) "
                                                                    + "VALUES(:reservationNum, :exchangeDate, :exchangeCode) ";
    /** 환전 코드를 통한 예약 정보 조회 */
    public static final String SELECT_RESERVATION_INFO_BY_CODE = "SELECT ri.num, ri.applicant, ri.nation_code, ri.price, ri.departure_date "
                                                                    + " FROM reservation_info AS ri "
                                                                    + " JOIN confirmed_exchange_info AS cei "
                                                                    + " ON ri.num = cei.reservation_num "
                                                                    + " AND cei.exchange_code = :exchangeCode "
                                                                    + " AND ri.progress = 1";
    /** 고객 ID, 예약 번호를 통한 예약 정보 조회 */
    public static final String SELECT_RESERVATION_INFO_BY_ID_AND_NUM = "SELECT num, nation_code, price FROM reservation_info WHERE applicant = :userId AND num = :reservationNum";
    /** 예약 정보 추가 */
    public static final String INSERT_RESERVATION_INFO = "INSERT INTO reservation_info(applicant, price, departure_date, nation_code) VALUES(:userId, :price, :departureDate, :nationCode)";
    /** 환전일 확정 예약 조회 */
    public static final String SELECT_CONFIRMED_EXCHANGE_INFO = "SELECT exchange_date, exchange_code FROM confirmed_exchange_info WHERE reservation_num = :reservationNum";


    /** 고객 정보 추가 */
    public static final String INSERT_CUSTOMER = "INSERT INTO customer(id, pw, name, account) VALUES(:userId, :userPw, :userName, :userAccount)";
    /** 고객 정보 존재 여부 조회 */
    public static final String SELECT_EXISTS_CUSTOMER = "SELECT EXISTS(SELECT * FROM customer WHERE id = :userId AND pw = :userPw)";


    /** 고객 ID를 통한 목표 환율 조회 */
    public static final String SELECT_TARGET_RATES_BY_ID = "SELECT nation_code, id, rate FROM target_rate WHERE id = :userId";
    /** 고객 ID, 국가 코드를 통한 목표 환율 상세 조회 */
    public static final String SELECT_TARGET_RATE = "SELECT id, nation_code, rate FROM target_rate WHERE id = :userId AND nation_code = :nationCode";
    /** 목표 환율 추가 */
    public static final String INSERT_TARGET_RATE = "INSERT INTO target_rate(id, nation_code, rate) VALUES(:userId, :nationCode, :rate)";
    /** 목표 환율 삭제 */
    public static final String DELETE_TARGET_RATE = "DELETE FROM target_rate WHERE id = :userId AND nation_code = :nationCode";
    /** 목표 환율 수정 */
    public static final String UPDATE_TARGET_RATE = "UPDATE target_rate SET nation_code = :nationCode, rate = :rate WHERE id = :userId AND nation_code = :originNationCode";
}
