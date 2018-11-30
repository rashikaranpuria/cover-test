# Cover Test [![Build Status](https://travis-ci.org/rashikaranpuria/cover-test.svg?branch=master)](https://travis-ci.org/rashikaranpuria/cover-test)
An application that contains 2 screens that ask autocomplete questions. One screen uses the Google Maps address API and the other uses a local array.

## Build Instructions
### Places API Key is required.

In order for this app to function properly please add your Places API key in local.properties like below.

Include the unique key for the build by adding the following line to [USER_HOME]/local.properties

`PLACES_API_KEY=UNIQUE_API_KEY`

## Important features
* **Code follows** *MVP* **architecture**
* **100%** *Kotlin* **code**
* **Asynchronous code using** *RxKotlin and RxAndroid*
* **Used** *RxBindings* **for rective UI**
* **Wrote unit tests using** *JUnit, Mockito & Robolectric*
* **Used** *Dagger 2* **for dependency injection**
* *Ktlint* **as static code analyzer**
* **Continous Integration with** *Travis CI*
* *Anko* **library**
* **Used** *Retrofit* **for network requests**
* **Follows** *Material Design principles*

## UI screens and flow
#### Empty Input
<img src="https://user-images.githubusercontent.com/1063696/48273411-d9d39f80-e40e-11e8-9931-2b8e08be2d90.jpg" width="250" />

#### Autocomplete Results
<img src="https://user-images.githubusercontent.com/1063696/48273510-1dc6a480-e40f-11e8-9174-5cf206ac9dd0.jpg" width="250" />

#### No Selection Error*
<img src="https://user-images.githubusercontent.com/1063696/48273583-4353ae00-e40f-11e8-977d-f05ab87417c6.jpg" width="250" />

#### Empty Input
<img src="https://user-images.githubusercontent.com/1063696/48273624-62ead680-e40f-11e8-8229-bdf409a1ab60.jpg" width="250" />

#### Autocomplete Results
<img src="https://user-images.githubusercontent.com/1063696/48273673-7c8c1e00-e40f-11e8-9c21-0dfc48ecc729.jpg" width="250" />

#### No Selection Error*
<img src="https://user-images.githubusercontent.com/1063696/48273583-4353ae00-e40f-11e8-977d-f05ab87417c6.jpg" width="250" />

#### Basic Workflow
<img src="https://user-images.githubusercontent.com/1063696/48276312-b102d880-e415-11e8-8111-c50ff256ff6f.png" width="250" />
