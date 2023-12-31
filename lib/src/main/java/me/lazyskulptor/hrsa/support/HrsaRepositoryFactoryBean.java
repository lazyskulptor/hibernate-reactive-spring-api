package me.lazyskulptor.hrsa.support;

import org.hibernate.reactive.mutiny.Mutiny;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import java.io.Serializable;

public class HrsaRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable>
        extends RepositoryFactoryBeanSupport<T, S, ID> {
    private final Mutiny.SessionFactory sessionFactory;

    private final SessionDispatcher dispatcher;

    /**
     * Creates a new {@link RepositoryFactoryBeanSupport} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     * @param sessionFactory
     * @param dispatcher
     */
    protected HrsaRepositoryFactoryBean(Class<? extends T> repositoryInterface, Mutiny.SessionFactory sessionFactory, SessionDispatcher dispatcher) {
        super(repositoryInterface);
        this.sessionFactory = sessionFactory;
        this.dispatcher = dispatcher;
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory() {
        return new HBReactiveRepositoryFactory(this.sessionFactory, this.dispatcher);
    }
}
