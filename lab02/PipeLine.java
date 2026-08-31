import java.util.ArrayList;
import java.util.List;

public class PipeLine<T, R> {

    private List<Transformer<?, ?>> transformers;

    public PipeLine() {
        transformers = new ArrayList<>();
    }

    private PipeLine(List<Transformer<?, ?>> transformers) {
        this.transformers = transformers;
    }

    public <U> PipeLine<T, U> addTransformer(Transformer<R, U> transformer) {

        List<Transformer<?, ?>> newTransformers =
                new ArrayList<>(transformers);

        newTransformers.add(transformer);

        return new PipeLine<>(newTransformers);
    }

    @SuppressWarnings("unchecked")
    public R execute(T input) {

        Object result = input;

        for (Transformer<?, ?> transformer : transformers) {
            Transformer<Object, Object> currentTransformer =
                    (Transformer<Object, Object>) transformer;

            result = currentTransformer.transform(result);
        }

        return (R) result;
    }
}