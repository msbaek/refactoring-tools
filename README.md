# Refactoring With IntelliJ Refactoring Tools

이 글은 Intellij의 Refactoring 메뉴에 있는 모든 Refactoring 기능들을 jetbrains 사이트의 예제 및 일부 보완한 예제를 통해 리팩토링시 주의 사항이나 전후를 비교한다.
com.example.refactorings.RefactoringTest 의 각 테스트를 따라서 보면서 화면과 같이 변화가 일어나도록 진행하면 됨

## 목차

- [Change Signature](#change-signature)
- [Convert Anonymous to Inner Class](#convert-anonymous-to-inner-class)
- [Convert to Instance Method](#convert-to-instance-method)
- [Encapsulate Fields](#encapsulate-fields)
- [Introduce Constants](#introduce-constants)
- [Introduce Field](#introduce-field)
- [Extract Interface](#extract-interface)
- [Extract Method](#extract-method)
- [Extract Super Class](#extract-super-class)
- [Introduce Variable](#introduce-variable)
- [introduce parameter](#introduce-parameter)
- [Extract Delegate](#extract-delegate)
- [Replace Method with Method Object](#replace-method-with-method-object)
- [Replace Conditional Logic with Strategy Pattern](#replace-conditional-logic-with-strategy-pattern)
- [Generify](#generify)
- [Inline Constructor](#inline-constructor)
- [Inline Superclass](#inline-superclass)
- [Inline To Anonymous Class](#inline-to-anonymous-class)
- [Invert Boolean](#invert-boolean)
- [Make Static](#make-static)
- [Migrate](#migrate)
- [Copy Class](#copy-class)
- [Move Method](#move-method)
- [Pull Members Up](#pull-members-up)
- [Remove Middleman](#remove-middleman)
- [Replace Constructor with Builder](#replace-constructor-with-builder)
- [Replace Constructor with Factory Method](#replace-constructor-with-factory-method)
- [Replace Inheritance with Delegation](#replace-inheritance-with-delegation)
- [Replace Temp with Query](#replace-temp-with-query)
- [Type Migration](#type-migration)
- [Wrap Return Value](#wrap-return-value)

## Change Signature

![](images/fig1.png)

- Cage 클래스의 생성자에 height를 추가

## Convert Anonymous to Inner Class

![](images/fig2.png)

- new나 생성자(Int())에 커서를 위치시키고 리팩토링

## Convert to Instance Method

![](images/fig3.png)

- 메소드명에 커서를 위치시키고 리팩토링

## Encapsulate Fields

![](images/fig4.png)

- 필드명에 커서를 위치시키고 리팩토링

## Introduce Constants

- 변수(“string”)에 커서를 위치시키고 리팩토링

## Introduce Field

- 변수(anotherClass.intValue())를 선태하고 리팩토링

## Extract Interface

![](images/fig5.png)

![](images/fig5-1.png)

## Extract Method

![](images/fig6.png)

## Extract Super Class

![](images/fig7.png)

## Introduce Variable

![](images/fig8.png)

## Introduce Parameter

![](images/fig9.png)

## Extract Delegate

![](images/fig10.png)

![](images/fig10-1.png)

![](images/fig10-2.png)

## Replace Method with Method Object

![](images/fig11.png)

## Replace Conditional Logic with Strategy Pattern

1. Replace Method with Method Object

![](images/fig12-1.png)

2. Generalize Algorithm

![](images/fig12-2.png)

3. Extract methods and implement them by copy & paste

![](images/fig12-3.png)

4. Create Factory Method

![](images/fig12-4.png)

5. Create Subclasses by Show Context Actions

![](images/fig12-5.png)

6. Push Members Down

![](images/fig12-6.png)

7. Remove Unused code using coverage

## Generify

![](images/fig13.png)

## Inline Constructor

![](images/fig14.png)

## Inline Superclass

![](images/fig15-1.png)

![](images/fig15-2.png)

## Inline To Anonymous Class

![](images/fig16.png)

## Invert Boolean

![](images/fig17.png)

## Make Static

![](images/fig18.png)

## Migrate

![](images/fig19-1.png)

![](images/fig19-2.png)

## Copy Class

![](images/fig20.png)

## Move Method

![](images/fig21-1.png)

- 레퍼런스 타입이 없는 경우는 static으로 변환 후 이동

![](images/fig21-2.png)

## Pull Members Up

![](images/fig22.png)

## Remove Middleman

![](images/fig23.png)

## Replace Constructor with Builder

![](images/fig24.png)

## Replace Constructor with Factory Method

![](images/fig25.png)

## Replace Inheritance with Delegation

![](images/fig26-1.png)

![](images/fig26-2.png)

## Replace Temp with Query

![](images/fig27.png)

## Type Migration

![](images/fig28.png)

## Wrap Return Value

![](images/fig29.png)