package com.example;

import edelta.lib.AbstractEdelta;
import edelta.refactorings.lib.EdeltaBadSmellsResolver;
import org.eclipse.emf.ecore.EPackage;

@SuppressWarnings("all")
public class Example extends AbstractEdelta {
  private EdeltaBadSmellsResolver resolver;
  
  public Example() {
    resolver = new EdeltaBadSmellsResolver(this);
  }
  
  public Example(final AbstractEdelta other) {
    super(other);
    resolver = new EdeltaBadSmellsResolver(other);
  }
  
  public void cleanUp(final EPackage it) {
    this.resolver.resolveDeadClassifiers(it);
    getEAttribute("Persons", "Person", "fullName").setName("name");
  }
  
  @Override
  public void performSanityChecks() throws Exception {
    ensureEPackageIsLoaded("Persons");
    ensureEPackageIsLoaded("WebApp");
  }
  
  @Override
  protected void doExecute() throws Exception {
    cleanUp(getEPackage("Persons"));
  }
}
