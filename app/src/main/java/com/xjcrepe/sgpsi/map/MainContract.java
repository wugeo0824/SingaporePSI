package com.xjcrepe.sgpsi.map;

import com.xjcrepe.sgpsi.model.PsiReadings;
import com.xjcrepe.sgpsi.model.PsiReadingsType;

/**
 * Created by LiXijun on 2017/9/9.
 */

public interface MainContract {

    interface View {

        void showLoading();

        void hideLoading();

        void showPsiReadings(PsiReadings psiReadings);

        void showErrorMessage(String errorMsg);
    }

    interface Presenter {

        void bindView(View view);

        void unbindView();

        void fetchPsiReadingsOfType(@PsiReadingsType int type);
    }
}
