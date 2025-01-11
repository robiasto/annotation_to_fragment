package de.robiasto.app.infrastructure.utility.id;

public class RouteConfiguration {

    @SuppressWarnings("java:S1075")
    static final String ID_PATH = "/{id}";

    static final String ROOT_PATH = "{root}";

    public static final String IMG = "/img";

    public static final String CREATE = "/create";

    public static final String DELETE = "/delete" + ID_PATH;

    public static final String UPDATE = "/update" + ID_PATH;

    static final String AVATAR_IMG = IMG + ROOT_PATH + ID_PATH + ".jpg";

    RouteConfiguration() {
    }

    public static String getList(RouteIdInterface id) {
        return replaceRoot(ROOT_PATH, id);
    }

    public static String getCreate(RouteIdInterface id) {
        return RouteConfiguration.replaceRoot(ROOT_PATH + CREATE, id);
    }

    public static String getDelete(RouteIdInterface id) {
        return RouteConfiguration.replaceAll(DELETE, id);
    }

    public static String getUpdate(RouteIdInterface id) {
        return RouteConfiguration.replaceAll(UPDATE, id);
    }

    public static String getAvatar(RouteIdInterface id)
    {
        return replaceRoot(replaceId(AVATAR_IMG, id), id);
    }

    private static String replaceAll(String path, RouteIdInterface id) {
        return replaceRoot(replaceId(ROOT_PATH + path, id), id);
    }

    private static String replaceId(String path, RouteIdInterface id) {
        return path.replaceFirst("\\{id}", id.toString());
    }

    private static String replaceRoot(String path, RouteIdInterface id) {
        return path.replaceFirst("\\{root}", id.getRouteBasePath());
    }
}
