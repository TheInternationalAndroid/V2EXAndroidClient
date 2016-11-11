package ${packageName}.contract;

import ${libPackage}.di.scope.PerFragment;
import ${libPackage}.view.base.comp.FragmentComp;
import ${libPackage}.presenter.IPresenter;
import ${libPackage}.view.base.view.IView;
import ${packageName}.vm.module.${pageName}VMModule;
import ${packageName}.${fragmentName};

import dagger.Component;

public interface ${pageName}Contract {

    @PerFragment
    @Component(modules = {${pageName}VMModule.class}, dependencies = FragmentComp.class)
    interface Comp extends FragmentComp {
        void inject(${fragmentName} fragment);
    }

    interface View extends IView {
    }

    interface Presenter extends IPresenter {

    }

}
