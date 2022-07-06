package github.data.thread

import com.example.example.Repositories
import com.example.github.UserInfo
import com.example.github.Worker
import com.example.github.WorkerInterface
import java.lang.Thread.sleep

class MainThread(private val userName: String) : WorkerInterface<Repositories> {
    var worker: Worker? = null
    var repositoriesOld : Array<Repositories>? = null
    var notifyUpdate: ((Array<Repositories>) -> Unit) = {}

    init {
        worker = Worker(this)
    }

    suspend fun invoke() {
        while (true) {
            worker?.let {
                it.requestInfo(userName)

                sleep(1000)
            }
        }
    }

    override fun notifyDataUpdated(userInfo: UserInfo?, repositories: Array<Repositories>?) {
        if (repositoriesOld == null || (repositories.contentEquals(repositoriesOld))) {
            repositoriesOld = repositories
            repositories?.let {
                notifyUpdate.invoke(repositories)
            }
        }
    }

}