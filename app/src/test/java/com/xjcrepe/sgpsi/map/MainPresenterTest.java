package com.xjcrepe.sgpsi.map;

import com.xjcrepe.sgpsi.model.PsiReadings;
import com.xjcrepe.sgpsi.model.PsiReadingsType;
import com.xjcrepe.sgpsi.repo.PsiReadingsRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Single;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by LiXijun on 2017/9/10.
 */
public class MainPresenterTest {

    private MainPresenter subject;

    @Mock
    private PsiReadingsRepository psiReadingsRepository;

    @Mock
    private MainContract.View view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        subject = new MainPresenter(psiReadingsRepository);
        subject.bindView(view);
    }

    @Test
    public void fetchPsiReadingsOfType_onStart_showsLoadingOnView() {
        int readingType = PsiReadingsType.CO8HourMax;
        when(psiReadingsRepository.getPsiReadingsWithType(readingType))
                .thenReturn(Single.just(mock(PsiReadings.class)));

        subject.fetchPsiReadingsOfType(readingType);

        verify(view).showLoading();
    }

    @Test
    public void fetchPsiReadingsOfType_onSuccess_showsPsiReadingsOnView() {
        int readingType = PsiReadingsType.CO8HourMax;
        PsiReadings psiReadings = mock(PsiReadings.class);
        when(psiReadingsRepository.getPsiReadingsWithType(readingType))
                .thenReturn(Single.just(psiReadings));

        subject.fetchPsiReadingsOfType(readingType);

        verify(view).showPsiReadings(psiReadings);
    }

    @Test
    public void fetchPsiReadingsOfType_onSuccess_hidesLoading() {
        int readingType = PsiReadingsType.CO8HourMax;
        when(psiReadingsRepository.getPsiReadingsWithType(readingType))
                .thenReturn(Single.just(mock(PsiReadings.class)));

        subject.fetchPsiReadingsOfType(readingType);

        verify(view).hideLoading();
    }

    @Test
    public void fetchPsiReadingsOfType_onError_showsErrorMessageOnView() {
        int readingType = PsiReadingsType.CO8HourMax;
        String errorMessage = "fake_message";
        Throwable error = new Throwable(errorMessage);
        when(psiReadingsRepository.getPsiReadingsWithType(readingType))
                .thenReturn(Single.<PsiReadings>error(error));

        subject.fetchPsiReadingsOfType(readingType);

        verify(view).showErrorMessage(errorMessage);
    }

    @Test
    public void fetchPsiReadingsOfType_onError_hidesLoading() {
        int readingType = PsiReadingsType.CO8HourMax;
        Throwable error = new Throwable();
        when(psiReadingsRepository.getPsiReadingsWithType(readingType))
                .thenReturn(Single.<PsiReadings>error(error));

        subject.fetchPsiReadingsOfType(readingType);

        verify(view).hideLoading();
    }
}