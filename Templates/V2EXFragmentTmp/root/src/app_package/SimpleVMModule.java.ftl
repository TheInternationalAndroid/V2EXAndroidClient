package ${packageName}.vm.module;

import ${libPackage}.di.scope.PerFragment;
import ${packageName}.contract.${pageName}Contract;
import ${packageName}.presenter.${pageName}P;
import ${packageName}.vm.${pageName}VM;

import dagger.Module;
import dagger.Provides;

@Module
public class ${pageName}VMModule {

	private ${pageName}Contract.View view;

	public ${pageName}VMModule(${pageName}Contract.View view){
		this.view=view;
	}

	@Provides
	@PerFragment
	${pageName}VM provideVM(${pageName}P presenter){
		return new ${pageName}VM(presenter,view);
	}

}
