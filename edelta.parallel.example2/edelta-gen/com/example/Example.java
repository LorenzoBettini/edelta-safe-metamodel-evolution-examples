package com.example;

import edelta.lib.AbstractEdelta;
import edelta.refactorings.lib.EdeltaRefactorings;
import org.eclipse.emf.ecore.EPackage;

@SuppressWarnings("all")
public class Example extends AbstractEdelta {
  private EdeltaRefactorings refactorings;
  
  public Example() {
    refactorings = new EdeltaRefactorings(this);
  }
  
  public Example(final AbstractEdelta other) {
    super(other);
    refactorings = new EdeltaRefactorings(other);
  }
  
  public void cleanUp(final EPackage it) {
    this.refactorings.inlineClass(getEClass("Persons", "CreditCard"), "card_");
  }
  
  @Override
  public void performSanityChecks() throws Exception {
    ensureEPackageIsLoaded("Persons");
  }
  
  @Override
  protected void doExecute() throws Exception {
    cleanUp(getEPackage("Persons"));
  }
}
