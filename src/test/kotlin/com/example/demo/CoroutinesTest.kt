package com.example.demo

import kotlinx.coroutines.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.concurrent.Executors

class CoroutinesTest {
    companion object {
        private fun runner(internal: () -> Unit) {
            println("===== start =====")
            internal()
            println("===== done =====")
        }

        private fun task1() {
            println("start task1 in Thread ${Thread.currentThread()}")
            println("end task1 in Thread ${Thread.currentThread()}")
        }

        private fun task2() {
            println("start task2 in Thread ${Thread.currentThread()}")
            println("end task2 in Thread ${Thread.currentThread()}")
        }

        private suspend fun yieldTask1() {
            println("start task1 in Thread ${Thread.currentThread()}")
            yield()
            println("end task1 in Thread ${Thread.currentThread()}")
        }

        private suspend fun yieldTask2() {
            println("start task2 in Thread ${Thread.currentThread()}")
            yield()
            println("end task2 in Thread ${Thread.currentThread()}")
        }
    }

    @Nested
    @DisplayName("동시 실행")
    inner class Concurrency {
        @Test
        fun `순차적 실행`() {
            runner {
                task1()
                task2()
                println("called task1 and task2 from ${Thread.currentThread()}")
            }
        }

        @Test
        fun `동시 실행`() {
            runner {
                runBlocking {
                    task1()
                    task2()
                    println("called task1 and task2 from ${Thread.currentThread()}")
                }
            }
        }

        @Test
        fun `두개의 다른 코루틴으로 실행`() {
            runner {
                runBlocking {
                    launch { task1() }
                    launch { task2() }
                    println("called task1 and task2 from ${Thread.currentThread()}")
                }
            }
        }

        @Test
        fun `서스펜션 포인트로 현재 실행중인 작업을 중지 시키고 다른 작업 수행`() {
            // delay() - 현재 실행중인 작업을 지정된 시간만큼 멈추게 함.
            // yield() - 명시적인 지연 x, 현재 작업이 더 중요한 작업들의 실행을 기다린다.
            runner {
                runBlocking {
                    yieldTask1()
                    yieldTask2()
                }
            }
        }
    }

    @Nested
    @DisplayName("코루틴 컨텍스트")
    inner class CoroutineContext {
        // Dispatchers.IO : IO작업을 실행을 위한 풀 안의 코루틴을 실행시키는데 사용될 수 있다. 이 풀은 스레드가 IO에 블록될 경우와 작업이 더 생성된 경우 사이즈가 커질 수 있다.
        // Dispatchers.Main : 안드로이드 기기와 Swing UI에서 사용. ex) main 스레드에서만 사용되는 UI 업데이트 기능을 실행하는 것이 있다.
        @Test
        fun `코루틴은 병렬 실행을 할 수도 있고, 동시 실행을 할 수도 있다, 이런 실행방식은 컨텍스트에 따라 달라진다`() {
            runner {
                runBlocking {
                    launch(Dispatchers.Default) { yieldTask1() } // 병렬 실행
                    launch { yieldTask2() } // 람다 내부의 코드와 함께 동시 실행
                    println("called task1 and task2 from ${Thread.currentThread()}")
                }
            }
        }

        @Test
        fun `커스텀 싱글 스레드 풀에서 코루틴 실행 시 병렬 실행이 아닌 동시 실행`() {
            Executors.newSingleThreadExecutor()
                .asCoroutineDispatcher()
                .use { context ->
                    runner {
                        runBlocking {
                            launch(context) { yieldTask1() }
                            launch { yieldTask2() }
                            println("called task1 and task2 from ${Thread.currentThread()}")
                        }
                    }
                }
        }

        @Test
        fun `서스펜션 포인트 이후 스레드 스위칭`() {
            // LAZY - 명시적으로 start() 가 호출되기 전까지 실행 연기
            // ATOMIC - 중단 할 수 없는 모드로 실행
            // UNDISPATCHED - 처음에는 현재 컨텍스트에서 실행, 서스펜션 포인트 이후 스레드를 스위치해서 실행
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
                .asCoroutineDispatcher()
                .use { context ->
                    runner {
                        runBlocking {
                            launch(context, CoroutineStart.UNDISPATCHED) { task1() }
                            launch { task2() }
                            println("called task1 and task2 from ${Thread.currentThread()}")
                        }
                    }
                }
        }

        @Test
        fun `코루틴 컨텍스트 변경`() {
            // withContext -> 코드의 한 부분을 코루틴의 다른 코드들과 완전히 다른 컨텍스트에서 실행
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
            runner {
                runBlocking {
                    println("starting in Thread ${Thread.currentThread()}")
                    withContext(Dispatchers.Default) { task1() }
                    launch { task2() }
                    println("ending in Thread ${Thread.currentThread()}")
                }
            }
        }
    }

    @Nested
    @DisplayName("코루틴 비동기") // Deferred<T> 퓨처 객체 반환
    internal class Asynchronous {
        @Test
        fun `Async`() {
            runBlocking {
                val count: Deferred<Int> = async(Dispatchers.Default) {
                    println("fetching in ${Thread.currentThread()}")
                    Runtime.getRuntime().availableProcessors() // 현재 사용 가능한 코어의 숫자
                }
                println("Called the function is ${Thread.currentThread()}")
                println("Number of cores is ${count.await()}")
            }
        }
    }
}