package com.example.leandesignpattern.observer

interface Chart {
    fun draw()
}

data class FavoriteFilm(val type: String, val percent: Float)

class Observer<T>(val onChange: (T) -> Unit)

// liveData MutableLiveData
class Subject<T>(initialValue: T) {
    private val listObserver = mutableListOf<Observer<T>>()

    var value: T = initialValue
        set(value) {
            field = value
            listObserver.forEach { observer ->
                observer.onChange(value)
            }
        }

    fun observer(observer: Observer<T>) {
        listObserver.add(observer)
    }

}

data class UserAge(val age: Int, val percent: Float)

class ViewModel {
    //    val listFavoriteFilms = mutableListOf<FavoriteFilm>()
    val listFavoriteFilms = Subject<List<FavoriteFilm>>(emptyList())
    val listFavoriteFilmObserver = mutableListOf<Observer<List<FavoriteFilm>>>()
    val listUserAge = mutableListOf<UserAge>()

    fun fetch() {

        val list = listOf(
            FavoriteFilm(type = "Action", percent = 50f),
            FavoriteFilm(type = "Comdy", percent = 20f)
        )
//        listFavoriteFilms.addAll(list)
        val currentList = listFavoriteFilms.value.toMutableList()
        currentList.addAll(list)
        listFavoriteFilms.value = currentList
//        listFavoriteFilmObserver.forEach {
//            observer->
//            observer.onChange(listFavoriteFilms)
    }
}

//    fun register(observer: Observer<List<FavoriteFilm>>){
//        listFavoriteFilmObserver.add(observer)
//   }
//}

class SpreadSheet(viewModel: ViewModel) : Chart {

    override fun draw() {
        TODO("Not yet implemented")
    }

}

class BarChart(viewModel: ViewModel) : Chart {
    init {
//        viewModel.register(observer = Observer { list ->
//
//        })

        viewModel.listFavoriteFilms.observer(Observer {

        })
    }

    override fun draw() {
        TODO("Not yet implemented")
    }
}

fun main() {
    val viewModel = ViewModel()
    // fragment
    val spreadSheet = SpreadSheet(viewModel = viewModel)
    val barChart = BarChart(viewModel = viewModel)

    spreadSheet.draw()
    barChart.draw()

    viewModel.fetch()
}