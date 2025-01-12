package problems.pubsub.models;

public enum Topic {
    BEAUTY {
        @Override
        public <T> T accept(TopicVisitor<T> visitor) {
            return visitor.visitBeauty();
        }
    },
    COOKING {
        @Override
        public <T> T accept(TopicVisitor<T> visitor) {
            return visitor.visitCooking();
        }
    },
    LIFESTYLE {
        @Override
        public <T> T accept(TopicVisitor<T> visitor) {
            return visitor.visitLifeStyle();
        }
    };

    public abstract <T> T accept(TopicVisitor<T> visitor);

    public interface TopicVisitor<T> {
        T visitBeauty();
        T visitCooking();
        T visitLifeStyle();
    }
}
