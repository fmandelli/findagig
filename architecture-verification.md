# The Art of Design 

To code is easy. 

The hard thing is how to make sense, how to model a complex domain with code. 

Our brain is fantastic, **but it has limitations**. It can't handle too many things and their connections at the same time if these things make no sense. They have to have an order, a logic behind, an overall idea that explains not every detail, but gives you a pattern, a map that simplifies the navigation through the details with powerful shortcuts. 

So, a layer that has no meaning to exists will be only one more thing that must be remembered for nothing but to flood your poor, limited brain. 

You need to pick up "abstractions" that represents the domain with fidelity, because this will make sense for the brain, this will be the overall idea that will connect the dots to keep them tie.

## About Software Dogmas

Some developers, especially the new ones, read about software development best practices and tend to apply it as something sacred.

For example, _DRY (Don't Repeat Yourself)_. It seems to be a very obvious advice to not reapeat code. 

Another concept that some people proclaim without questioning is the _SRP (Single Responsability Principle)_. If you want to know more about, search for SOLID - Clean Code. Also, very basic: A class must have one and only one reason to change (a single responsability). That prevents a very bad practices, to create huge classes that basic does everything.

These and others best practices are treated as dogmas, but there is no such thing in software development.

A better approach is described by [Kent Beck](https://martinfowler.com/bliki/BeckDesignRules.html) as the four rules of simple design:

>  1. Passes the tests
>  2. Reveals intention
>  3. No duplication
>  4. Fewest elements

There is a priority in these rules, so _pass the tests_ is more important than _reveals intention_. 

Also, _no duplication_ is important, but _reveals intention_ is more. So the DRY advice is not always the right thing to do.

The _fewest elements_ as the last rule is saying that, if you build something that doesn't help the prior rules, remove it. Again, another sacred rule, SRP, is not that sacred.

Except the first rule, all the rest is about how to design something understandable.

The sucess depends on how well design these abstractions are. The second part is: how to implement this abstractions.


## Domain Driven Design

### A Communication Language

It's inevitable to establish a kind of informal language when you are talking or writing about the domain you are modeling and coding. But this language must not be used only by developers and the understanding must be shared between all, business people included.  
Both groups must talk the very same language, all the terms must have the same meaning to everyone to describe the domain, not the technology you are going to use to code it.   
This is the **"Ubiquitous Language"**.


### DDD Layered Architecture
![logo](https://www.citerus.se/wp-content/uploads/2019/07/dddlayers.png "DD")

Pay attention to the dependency directions. 

Basically, the upper layer can access all the layers below, but not the inverse, as simple as that.

"_But would an UI component call directly the Domain layer_ ?", you would ask. 

> ANSWER: _**YES**_. 

But the right concern shouldn't be this.

The real bad thing is to implement business logic, which belongs to the Domain layer, in the UI Layer. As long as you don't do that, everything is fine.

The thing is: your layers must have a purpose, otherwise is just noise. All the abstraction that are not adding value, doing something that really matters, must be removed. 

> "_Why ?"_

## DDD Additional Concept: Modules

Modules are business concepts that can group and explain a 
certain part of your domain. 

In business terms, would be impossible to explain the domain without mentioned this business concepts. Yes, this is part of the   _"Ubiquitous Language"_.

As they are so important, It is mandatory to express them in your code. That's the essence of the DDD: To keep as much as possible your code align with the business understanding.

A non-trivial domain can have multiple modules, and every module must be design in a way that It's very cohesive, with low dependency between them. 

In java, a possible implementation would be to define packages as modules. All the classes related to that business concept need to be under this package.

