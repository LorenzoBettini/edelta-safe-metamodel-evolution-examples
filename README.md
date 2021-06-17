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


