package com.xjcrepe.sgpsi.repo;

import com.xjcrepe.sgpsi.model.PsiReadings;
import com.xjcrepe.sgpsi.network.PsiService;
import com.xjcrepe.sgpsi.network.response.PsiItemsResponse;
import com.xjcrepe.sgpsi.network.response.PsiReadingSetResponse;
import com.xjcrepe.sgpsi.network.response.PsiResponse;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Callable;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static com.xjcrepe.sgpsi.model.PsiReadingsType.O3SubIndex;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PsiReadingsRepositoryTest {

    @Mock
    private PsiService psiService;

    @Mock
    private PsiResponse mockResponse;

    private PsiReadingsRepository subject;

    @BeforeClass
    public static void setupMainScheduler() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        });

        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        subject = new PsiReadingsRepository(psiService);
    }

    @Test
    public void getPsiReadingsWithType_noCache_callsPsiService() {
        when(psiService.getPsi()).thenReturn(Single.just(mockResponse));

        subject.getPsiReadingsWithType(O3SubIndex);

        verify(psiService).getPsi();
    }

    @Test
    public void getPsiReadingsWithType_noCache_returnsCorrectReadings() {
        PsiReadings o3SubIndex = mock(PsiReadings.class);
        PsiItemsResponse itemsResponse = mock(PsiItemsResponse.class);
        PsiReadingSetResponse readingSetResponse = mock(PsiReadingSetResponse.class);
        when(readingSetResponse.getO3SubIndex()).thenReturn(o3SubIndex);
        when(itemsResponse.getReadingSet()).thenReturn(readingSetResponse);
        when(mockResponse.getItemsResponse()).thenReturn(itemsResponse);
        when(psiService.getPsi()).thenReturn(Single.just(mockResponse));

        TestObserver<PsiReadings> testObserver = subject.getPsiReadingsWithType(O3SubIndex).test();
        testObserver.awaitTerminalEvent();

        testObserver.assertValue(o3SubIndex);
    }

    @Test
    public void getPsiReadingsWithType_withCache_returnsCorrectReadingsFromCache() {
        PsiReadings o3SubIndex = mock(PsiReadings.class);
        PsiItemsResponse itemsResponse = mock(PsiItemsResponse.class);
        PsiReadingSetResponse readingSetResponse = mock(PsiReadingSetResponse.class);
        when(readingSetResponse.getO3SubIndex()).thenReturn(o3SubIndex);
        when(itemsResponse.getReadingSet()).thenReturn(readingSetResponse);
        when(mockResponse.getItemsResponse()).thenReturn(itemsResponse);
        when(psiService.getPsi()).thenReturn(Single.just(mockResponse));
        subject.getPsiReadingsWithType(O3SubIndex).subscribe();
        reset(psiService);

        TestObserver<PsiReadings> testObserver = subject.getPsiReadingsWithType(O3SubIndex).test();
        testObserver.awaitTerminalEvent();

        verify(psiService, never()).getPsi();
        testObserver.assertValue(o3SubIndex);
    }

    @Test
    public void getPsiReadingsWithType_invalidReadingType_returnsError() {
        PsiItemsResponse itemsResponse = mock(PsiItemsResponse.class);
        PsiReadingSetResponse readingSetResponse = mock(PsiReadingSetResponse.class);
        when(itemsResponse.getReadingSet()).thenReturn(readingSetResponse);
        when(mockResponse.getItemsResponse()).thenReturn(itemsResponse);
        when(psiService.getPsi()).thenReturn(Single.just(mockResponse));

        TestObserver<PsiReadings> testObserver = subject.getPsiReadingsWithType(O3SubIndex).test();
        testObserver.awaitTerminalEvent();

        testObserver.assertFailure(InvalidReadingTypeException.class);
    }
}
