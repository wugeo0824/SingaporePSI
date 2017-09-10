package com.xjcrepe.sgpsi.repo;

import com.xjcrepe.sgpsi.model.PsiReadings;
import com.xjcrepe.sgpsi.network.PsiService;
import com.xjcrepe.sgpsi.network.response.PsiItemsResponse;
import com.xjcrepe.sgpsi.network.response.PsiReadingSetResponse;
import com.xjcrepe.sgpsi.network.response.PsiResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

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
        TestObserver<PsiReadings> testObserver = TestObserver.create();

        subject.getPsiReadingsWithType(O3SubIndex).subscribe(testObserver);

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
        TestObserver<PsiReadings> testObserver = TestObserver.create();
        reset(psiService);

        subject.getPsiReadingsWithType(O3SubIndex).subscribe(testObserver);

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
        TestObserver<PsiReadings> testObserver = TestObserver.create();

        subject.getPsiReadingsWithType(O3SubIndex).subscribe(testObserver);

        testObserver.assertFailure(InvalidReadingTypeException.class);
    }
}
