# Swing and Model/View/Controller

All Swing components use M/V/C as their basic architecture, but it's somehow difficult to write an application which makes full use the MVC architecture. Nothing problematic with that: for instance, when dealing with forms, a Model/View/presenter approach might be more suitable - and definitly simpler.

The present project shows various approaches to full MVC in Swing, using various libraries. All example correspond to a simple CRUD application, backed by Spring. It's a work in progress, which has a number of shortcomings:

- there is no proper management of asynchronous operation. Currently, everything blocks until the result arrives;
- some cleanup might be useful; for instance, some objects might be injected by Spring instead of being created by hand;
- all data is stored in memory, which might not be reasonnable. To be honest, in the [Ramsès Project](https://ramses.ulg.ac.be/) I have created for the university of Liège, it's exactly what I do for encoders, but it's a relatively specific use case.

  

## The projects

Currently, I propose two implementations:

editor-swingfx
: uses two different libraries.

  - SwingFX, which allows to write Swing applications using JavaFX properties. Some parts are not very well developped, such as text fields processing, or collections;
  - regarding collections, I have used GlazedLists (in a very basic way).

editor-jgoodies
: uses JGoodies Binding. They cover all your needs. **SwingFx** is more modern, but doesn't cover all types of components. The problem for JGoodies is that its business model doesn't its support as open-source project - it used to be founded through training sessions and donations by some companies, but the audience for Swing is now too small to support it. 

## Overall architecture

Globally, the idea for both projects is the same.

A shared model contains the lists of data to display ; the program is split into three elements :

- a global list display, which also allows the deletion of items;
- a form for creating items;
- a form for editing and navigating between items.

Each of these forms has a *Controller* and is bound to a specific *ViewModel,* which is itself linked to the shared model.

In each form, every element is bound to an element of the `ViewModel` ; the *properties* of some elements, such as button activation, are also bound to boolean properties of the `ViewModel`. 

Note that we could also consider using *Action* objects. Logically, they could fit in the `ViewModel`. A problem is that an action might also use graphical elements (for instance a file selector), which would be architecturally unsound if it was in the `ViewModel`. In this case, the solution would be to abstract the said graphical element with an interface, but it would be a bit awkward.

## Related projects

The combination of [RxJava](https://github.com/ReactiveX/RxJava) and [RxSwing](https://github.com/ReactiveX/RxSwing) provides a possible solution for full MVC applications in Swing. This track has been thoroughly explored  by  [Peti Koch](https://github.com/Petikoch) in the project [Java MVVM with Swing, RxJava and RxSwing](https://github.com/Petikoch/Java_MVVM_with_Swing_and_RxJava_Examples), with pretty complex use cases (multiple concurrent tasks). M. Koch warns *DON'T use it in production, there are some open issues here*.

Obviously, most of this is a bit old fashioned.


