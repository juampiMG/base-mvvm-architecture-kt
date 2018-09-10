package com.jp.app.injector.module

import com.jp.data.ServerMock
import com.jp.data.repository.SampleRepository
import com.jp.domain.model.SampleDomain
import com.jp.domain.repository.ISampleRepository
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    internal fun sampleRepository(repository: SampleRepository): ISampleRepository {
        var repository = repository
        repository = Mockito.mock(SampleRepository::class.java)
        `when`<Single<List<SampleDomain>>>(repository.getSamples()).thenReturn(Single.just<List<SampleDomain>>(ServerMock.getListDomain()))
        return repository
    }
}
