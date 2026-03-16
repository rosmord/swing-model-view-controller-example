# Swing and Model/View/Controller

All Swing components use M/V/C as their basic architecture, but it's somehow difficult to write an application which makes full use the MVC architecture. Nothing problematic with that: for instance, when dealing with forms, a Model/View/presenter approach might be more suitable - and definitly simpler.

The present project shows various approaches to full MVC in Swing, using various libraries. All example correspond to a simple CRUD application, backed by Spring. It's a work in progress, which has a number of shortcomings:

- there is no proper management of asynchronous operation. Currently, everything blocs until the result arrives;
- some cleanup might be useful; for instance, some objects might be injected by Spring instead of being created by hand;
  

## The projects

Currently, I propose two implementations:

editor-swingfx
: uses two different libraries.

  - SwingFX, which allows to write Swing applications using JavaFX properties. Some parts are not very well developped, such as text fields processing, or collections ;
  - regarding collections, I have used GlazedLists (in a very basic way).

editor-jgoodies
: uses JGoodies Binding. They cover all your need, but are a bit less elegant and modern than SwingFx.

## Overall architecture

Globally, the idea for both projects is the same. Each field, widget or graphical component property (such as activation) is bound to an element in the ViewModel.



