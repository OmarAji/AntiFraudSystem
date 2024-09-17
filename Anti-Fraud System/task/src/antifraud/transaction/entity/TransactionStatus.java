package antifraud.transaction.entity;

public enum TransactionStatus {
    ALLOWED,
    PROHIBITED,
    MANUAL_PROCESSING,

    ;

    public static boolean isValidEnumValue(String value) {
        try {
            TransactionStatus.valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isEqual(String str) {
        return this.name().equals(str);
    }
}
