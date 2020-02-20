package findagig.batch.domain.factory;

import findagig.batch.domain.entity.Gig;

import java.util.List;

public interface GigsFactory<T> {

    List<Gig> createGigs(T t) throws Exception;
}
