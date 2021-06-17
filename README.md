# edelta-safe-metamodel-evolution-examples
Some examples of safe metamodel evolution with Edelta.

These are the instructions to reproduce and try the examples.

First of all, Edelta requires at least Java 11.

First of all, download an Eclipse distribution with Edelta installed from here https://sourceforge.net/projects/edelta/files/products (_at least version 2.6.0_). Unzip the distribution somewhere on your hard disk. (For macOS users: depending on the version of your macOS, when you try to run the edelta.app you may run into an error that says "the application is damaged and can't be opened". This problem can be overcome by running the following command from the terminal (from the directory where the edelta.app is located): xattr -d edelta.app.)
Alternatively, install Edelta in your Eclipse distribution using this update site https://lorenzobettini.github.io/edelta-releases/.

Run our Eclipse distribution and choose any workspace location (or accept the default `workspace-edelta`).

Clone this repository (`git clone https://github.com/LorenzoBettini/edelta-safe-metamodel-evolution-examples.git`) in your hard disk.

Import the two projects in your Eclipse workspace (_Import_ => _General_ => _Existing Projects into Workspace_, and select the location where you cloned this repository). Wait for Eclipse to build the projects.

![image](https://user-images.githubusercontent.com/1202254/122388881-994a4400-cf70-11eb-92a3-01a51e45faa3.png)

In the first example project, the metamodel `Persons` (stored in `PersonsMM.ecore`) has a _Dead classifier_, `NameElement`, that is, a type that it is not used by any other type in `Persons`. By using the Edelta predefined function `resolveDeadClassifier`, such classifier would be removed from `Persons`, if it is run only on that metamodel. However, the metamodel `WebApp` actually uses `NameElement` (you can inspect the ecore: its classes `WebApp` and `Service` both extend `NameElement`). Removing `NameElement` from `Persons` would make `WebApp` invalid. If both metamodels are imported in the Edelta program, then `resolveDeadClassifier` would not consider `NameElement` as _dead_, since it's actually used by one of the metamodel in the resource set. In fact, from the Outline view above, you can verify that `NameElement` is still shown in the live preview of the evolved metamodel.

If you remove the metamodel import `metamodel "WebApp"` from the Edelta program (or comment it, as suggested by the comment) you can verify that `NameElement` would be actually removed (it disappears from the outline). However, doing that is not safe for `WebApp`. That's why both metamodels must be imported in the Edelta program:

![image](https://user-images.githubusercontent.com/1202254/122390234-0d391c00-cf72-11eb-9cb8-b96c15f0523c.png)

You can run the Edelta dependency analyzer on `PersonsMM.ecore` that will show its dependencies (none in this case) and the metamodels in the same folder that depends on it (`WebApp` in this case). Right click on `PersonsMM.ecore` and select _Edelta_ => _Analyzer Ecore Files_:

![image](https://user-images.githubusercontent.com/1202254/122390636-83d61980-cf72-11eb-819e-9b1045213b02.png)

A graph model will be created (in the directory `analysis/results`) and the dependencies will be shown in the _Picto_ view: the metamodel on which the analysis has been executed is shown in red:

![image](https://user-images.githubusercontent.com/1202254/122391124-fd6e0780-cf72-11eb-93d9-e39229d6be12.png)

**IMPORTANT:** The _Picto_ view renders HTML contents relying on CSS and JavaScript downloaded from the Internet, so you need an active Internet connection while trying this.

By using the other context menu _Generate Edelta Template File_, an `.edelta` file will be generated in the `src` folder, with all the needed metamodel imports to safely evolve the metamodel. In this example they will be `Persons` and `WebApp` for what we said above. The generated `.edelta` file is only a template: it should be renamed to something else before starting editing it.
