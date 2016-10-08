package ${packageName}.contract;

import ${libPackage}.di.scope.PerActivity;
import ${libPackage}.view.base.comp.ActivityComp;
import ${libPackage}.view.base.presenter.IPresenter;
import ${libPackage}.view.base.view.IView;
import ${packageName}.vm.module.${pageName}VMModule;
import ${packageName}.${activityClass};

import dagger.Component;

public interface ${pageName}Contract {

    @PerActivity
    @Component(modules = {${pageName}VMModule.class}, dependencies = ActivityComp.class)
    interface Comp extends ActivityComp {
        void inject(${activityClass} activity);
    }

    interface View extends IView {
    }

    interface Presenter extends IPresenter {

    }

}
