package github.domain.usecase

import com.example.example.Repositories

interface RequestRepositoriesUseCase {
    fun invoke() : Array<Repositories>
}