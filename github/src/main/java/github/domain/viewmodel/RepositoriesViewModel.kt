package github.domain.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.Repositories
import com.example.github.WorkerInterface
import github.data.thread.MainThread

class RepositoriesViewModel(val ownerName: String): ViewModel(), WorkerInterface<Repositories> {
    private val mainThread: MainThread = MainThread(ownerName)

    val repositories: MutableLiveData<Array<Repositories>> by lazy {
        MutableLiveData<Array<Repositories>>()
    }

    suspend fun invoke () {
        mainThread.notifyUpdate = {
            repositories.value = it
        }
        mainThread.invoke()
    }
}