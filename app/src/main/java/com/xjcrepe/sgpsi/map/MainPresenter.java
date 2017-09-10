package com.xjcrepe.sgpsi.map;

import android.support.annotation.Nullable;

import com.xjcrepe.sgpsi.model.PsiReadings;
import com.xjcrepe.sgpsi.model.PsiReadingsType;
import com.xjcrepe.sgpsi.repo.PsiReadingsRepository;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by LiXijun on 2017/9/10.
 */

public class MainPresenter implements MainContract.Presenter {

    @Nullable
    private MainContract.View view;

    private final PsiReadingsRepository psiReadingsRepository;

    @Nullable
    private Disposable psiReadingDisposable;

    @Inject
    public MainPresenter(PsiReadingsRepository psiReadingsRepository) {
        this.psiReadingsRepository = psiReadingsRepository;
    }

    @Override
    public void bindView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        if (psiReadingDisposable != null && !psiReadingDisposable.isDisposed()){
            psiReadingDisposable.dispose();
        }
        view = null;
    }

    @Override
    public void fetchPsiReadingsOfType(@PsiReadingsType int type) {
        psiReadingsRepository.getPsiReadingsWithType(type)
                .subscribe(new SingleObserver<PsiReadings>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        if (view == null){
                            return;
                        }
                        view.showLoading();
                        psiReadingDisposable = disposable;
                    }

                    @Override
                    public void onSuccess(PsiReadings psiReadings) {
                        if (view == null){
                            return;
                        }
                        view.hideLoading();
                        view.showPsiReadings(psiReadings);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view == null){
                            return;
                        }
                        view.hideLoading();
                        view.showErrorMessage(e.getLocalizedMessage());
                    }
                });
    }

}
