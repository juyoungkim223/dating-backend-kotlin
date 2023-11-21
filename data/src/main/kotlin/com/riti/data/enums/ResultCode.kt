package com.riti.data.enums
enum class ResultCode(val resultCode: String, val resultMessage: String) {
    SUCCESS("0000", "Success"),
    UNKNOWN_SERVER_ERROR("E999", "Unknown server error"),
    INTERNAL_SERVER_ERROR("E500", "Internal server error"),
    /**
     * User
     */
    SEND_MAIL_FAILED("U001", "Send mail failed"),
    SAVE_AUTH_CODE_FAILED("U002", "Save auth code failed"),
    SEND_MAIL_FAILED_BY_EMAIL_TYPE("U003", "Send mail failed by email type"),
    FAIL_AUTHENTICATE_AUTH_CODE("U004", "Fail to authenticate auth code"),
    FAIL_TO_GET_USER("U005", "[API FAILED] getUser"),
    FAIL_TO_SAVE_USER("U006", "[API FAILED] saveUser"),
    FAIL_TO_AUTHENTICATE_EMAIL_CODE("U007", "[API FAILED] authenticateEmailCode"),
    FAIL_TO_SAVE_EMAIL_CODE("U008", "[API FAILED] saveEmailAuthCode"),
    FAIL_TO_UPDATE_USER("U009", "[API FAILED] updateUser"),
    FAIL_TO_DELETE_USER("U010", "[API FAILED] deleteUser"),
    FAIL_TO_SIGN_IN_USER("U011", "[API FAILED] signInUser"),
    FAIL_AUTHENTICATE_SIGN_IN_TOKEN("U012", "Fail to authenticate sign in token"),
    FAIL_AUTHENTICATE_SIGN_IN_TOKEN_BY_EXPIRE_TIME("U013", "Fail to authenticate sign in token by expire time"),
    FAIL_AUTHENTICATE_SIGN_IN_TOKEN_BY_DIFFERENT_SUBJECT("U014", "Fail to authenticate sign in token by different subject"),

    /**
     * Recommend
     */
    FAIL_TO_RECOMMEND_USER("U015", "Fail to recommend user"),

    /**
     * Message
     */
    FAIL_TO_UPDATE_MESSAGE_STATUS("M000", "Fail to update message status"),
    FAIL_TO_SAVE_MESSAGE("M000", "Fail to save message"),
    FAIL_TO_GET_MESSAGE_ROOM("M001", "Fail to get message room"),
    FAIL_TO_GET_MESSAGE("M001", "Fail to get message"),
    FAIL_TO_FETCH_AUTO_INCREMENT("M001", "fail to get auto increment id"),
    USER_DOES_NOT_HAVE_CHAT_ROOM("M005", "User does not have chat room"),
    /**
     * INVENTORY
     */
    FAIL_TO_AUTHENTICATE_INVENTORY_GOLD("P001", "Fail to authenticate gold in the inventory"),
    FAIL_TO_SAVE_INVENTORY("P002", "Fail to save inventory"),
    FAILED_BECAUSE_OF_NOT_ENOUGH_GOLD("P003", "Failed because of not enough gold"),

    /**
     * FCM
     */
    FAIL_TO_GET_FCM_TOKEN("N000", "Fail to get fcm token"),
    FAIL_TO_SAVE_FCM_TOKEN("N001", "Fail to save fcm token"),

    /**
     * JSON
     */
    JSON_PARSE_ERROR("P001", "Json parse error");
    companion object {
        fun of(resultCode : String): ResultCode {
            ResultCode.values().iterator().forEach {
                if(resultCode == it.resultCode) return it
            }
            return UNKNOWN_SERVER_ERROR
        }
    }
}
