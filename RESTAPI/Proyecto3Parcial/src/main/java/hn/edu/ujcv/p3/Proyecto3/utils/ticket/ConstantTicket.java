package hn.edu.ujcv.p3.Proyecto3.utils.ticket;

public final class ConstantTicket {
    private static final String URL_API_BASE = "/api";
    private static final String URL_API_VERSION = "/v1";
    private static final String URL_BASE  = URL_API_BASE+URL_API_VERSION;
    public static  final String URL_BASE_TICKETS  = String.format("%s/tickets" , URL_BASE);
}
