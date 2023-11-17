package christmas.domain.badge;

public class BadgeProvider {
    public Badge provide(int amount) {
        if (amount >= 20_000) {
            return Badge.SANTA;
        }
        if (amount >= 10_000) {
            return Badge.TREE;
        }
        if (amount >= 5_000) {
            return Badge.STAR;
        }
        return Badge.NONE;
    }
}
