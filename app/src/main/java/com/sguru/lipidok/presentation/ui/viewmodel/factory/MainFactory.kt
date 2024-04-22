package com.sguru.lipidok.presentation.ui.viewmodel.factory

import com.sguru.lipidok.presentation.ui.model.Answer
import com.sguru.lipidok.presentation.ui.model.IndividualSelectionAnswer
import com.sguru.lipidok.presentation.ui.model.IndividualSelectionQuestion
import com.sguru.lipidok.presentation.ui.model.LipidProfileQuestions
import com.sguru.lipidok.presentation.ui.model.LipidProfileQuestionsResult
import com.sguru.lipidok.presentation.ui.model.LipidProfileResult
import com.sguru.lipidok.presentation.ui.model.LipidRiskGroupType

internal class MainFactory {
    internal fun getReadyLipidProfileQuestions(): List<LipidProfileQuestions> {
        return buildList {
            add(
                LipidProfileQuestions(
                    questions = "Пол",
                    answerOptions = listOf(
                        Answer(
                            answer = "Женский",
                            valuePoints = 0,
                        ),
                        Answer(
                            answer = "Мужской",
                            valuePoints = 5,
                        )
                    )
                )
            )
            add(
                LipidProfileQuestions(
                    questions = "Возраст",
                    answerOptions = listOf(
                        Answer(
                            answer = "Меньше 15 лет",
                            valuePoints = 0,
                        ),
                        Answer(
                            answer = "Больше или равно 15 лет",
                            valuePoints = 7
                        )
                    ),
                )
            )
            add(
                LipidProfileQuestions(
                    questions = "Ксантомы, ксантелазмы",
                    answerOptions = listOf(
                        Answer(
                            answer = "Отсутствуют",
                            valuePoints = 0,
                        ),
                        Answer(
                            answer = "Единичные",
                            valuePoints = 20,
                        ),
                        Answer(
                            answer = "Распространенные",
                            valuePoints = 30,
                        )
                    ),
                )
            )
            add(
                LipidProfileQuestions(
                    questions = "Артериальное давление",
                    answerOptions = listOf(
                        Answer(
                            answer = "Нормальное",
                            valuePoints = 0,
                        ),
                        Answer(
                            answer = "Высокое нормальное",
                            valuePoints = 4,
                        ),
                        Answer(
                            answer = "Артериальная гипертензия",
                            valuePoints = 7,
                        )
                    ),
                )
            )
            add(
                LipidProfileQuestions(
                    questions = "Индекс массы тела",
                    answerOptions = listOf(
                        Answer(
                            answer = "Нормальная масса тела",
                            valuePoints = 0,
                        ),
                        Answer(
                            answer = "Избыточная масса тела",
                            valuePoints = 17,
                        ),
                        Answer(
                            answer = "Ожирение",
                            valuePoints = 23,
                        )
                    ),
                )
            )
            add(
                LipidProfileQuestions(
                    questions = "Артериальная гипертензия у родственников",
                    answerOptions = listOf(
                        Answer(
                            answer = "Нет данных",
                            valuePoints = 0,
                        ),
                        Answer(
                            answer = "АГ у родственников 2 линии родства",
                            valuePoints = 3,
                        ),
                        Answer(
                            answer = "АГ у родственников 1 линии родства",
                            valuePoints = 5,
                        )
                    ),
                )
            )
            add(
                LipidProfileQuestions(
                    questions = "Акушерский анамнез",
                    answerOptions = listOf(
                        Answer(
                            answer = "Не отягощен",
                            valuePoints = 0,
                        ),
                        Answer(
                            answer = "Отягощен",
                            valuePoints = 4,
                        ),
                    ),
                )
            )
            add(
                LipidProfileQuestions(
                    questions = "ПП ЦНС в анамнезе",
                    answerOptions = listOf(
                        Answer(
                            answer = "Отсутствует",
                            valuePoints = 0,
                        ),
                        Answer(
                            answer = "Присутствует",
                            valuePoints = 4,
                        ),
                    ),
                )
            )
            add(
                LipidProfileQuestions(
                    questions = "Гидроцефальный синдром в анамнезе",
                    answerOptions = listOf(
                        Answer(
                            answer = "Отсутствует",
                            valuePoints = 0,
                        ),
                        Answer(
                            answer = "Присутствует",
                            valuePoints = 5,
                        ),
                    ),
                )
            )
        }
    }

    fun getReadyLipidProfileResult(valuePoints: Int): LipidProfileResult {
        return when (valuePoints) {
            in 75..90 -> {
                getLipidProfileResult(LipidRiskGroupType.VERT_TALL)
            }

            in 60..75 -> {
                getLipidProfileResult(LipidRiskGroupType.HIGH)
            }

            in 30..60 -> {
                getLipidProfileResult(LipidRiskGroupType.MEDIUM)
            }

            in 0..30 -> {
                getLipidProfileResult(LipidRiskGroupType.LOW)
            }

            else -> {
                getLipidProfileResult(LipidRiskGroupType.VERT_TALL)
            }
        }
    }


    internal fun getReadyLipidProfileQuestionsResult(): List<LipidProfileQuestionsResult> {
        return getReadyLipidProfileQuestions().map {
            LipidProfileQuestionsResult(
                questions = it.questions,
                valuePoints = null
            )
        }
    }

    // могут быть только два уровня
    internal fun getIndividualSelectionQuestions(): IndividualSelectionQuestion {
        return IndividualSelectionQuestion(
            text = "Применяемая гиполипидемическая терапия",
            answerOptions = listOf(
                // 1 ветка
                IndividualSelectionQuestion(
                    text = "Нет",
                    answerOptions = listOf(
                        IndividualSelectionQuestion(
                            text = "Противопоказания к использованию статинов?",
                            answerOptions = listOf(
                                IndividualSelectionQuestion(
                                    text = "Нет",
                                    answerOptions = listOf(
                                        IndividualSelectionQuestion(
                                            text = "Ловастатин 0,5 мг/кг 1 раз в сутки",
                                            answerOptions = null
                                        )
                                    )
                                ),
                                IndividualSelectionQuestion(
                                    text = "Да",
                                    answerOptions = listOf(
                                        IndividualSelectionQuestion(
                                            text = "Эзетимиб 10 мг 1 раз в сутки",
                                            answerOptions = null
                                        )
                                    )
                                )
                            )
                        ),
                    )
                ),
                // 2 ветка
                IndividualSelectionQuestion(
                    text = "Статины не в максимальной дозе",
                    answerOptions = listOf(
                        IndividualSelectionQuestion(
                            text = "Достигнут ли целевой уровень показателей " +
                                    "липидного профиля ?",
                            answerOptions = listOf(
                                IndividualSelectionQuestion(
                                    text = "Нет",
                                    answerOptions = listOf(
                                        IndividualSelectionQuestion(
                                            text = "Ловастатин 0,5 мг/кг 1 раз в сутки или" +
                                                    "Розувастатин до 20 мг 1 раз в сутки или" +
                                                    "Аторвастатин до 20 мг 1 раз в сутки",
                                            answerOptions = null
                                        )
                                    )
                                ),
                                IndividualSelectionQuestion(
                                    text = "Да",
                                    answerOptions = listOf(
                                        IndividualSelectionQuestion(
                                            text = getTextIndividualSelectionQuestion(),
                                            answerOptions = null
                                        )
                                    )
                                )
                            )
                        ),
                    )
                ),
                //3 ветка
                IndividualSelectionQuestion(
                    text = "Статины в максимальной дозе",
                    answerOptions = listOf(
                        IndividualSelectionQuestion(
                            text = "Достигнут ли целевой уровень показателей " +
                                    "липидного профиля ?",
                            answerOptions = listOf(
                                IndividualSelectionQuestion(
                                    text = "Нет",
                                    answerOptions = listOf(
                                        IndividualSelectionQuestion(
                                            text = "Добавить Эзетимиб 10 мг 1 раз в сутки",
                                            answerOptions = null
                                        )
                                    )
                                ),
                                IndividualSelectionQuestion(
                                    text = "Да",
                                    answerOptions = listOf(
                                        IndividualSelectionQuestion(
                                            text = getTextIndividualSelectionQuestion(),
                                            answerOptions = null
                                        )
                                    )
                                )
                            )
                        ),
                    )
                ),
                // 4 ветка
                IndividualSelectionQuestion(
                    text = "Статины + Эзетемиб",
                    answerOptions = listOf(
                        IndividualSelectionQuestion(
                            text = "Достигнут ли целевой уровень показателей " +
                                    "липидного профиля ?",
                            answerOptions = listOf(
                                IndividualSelectionQuestion(
                                    text = "Нет",
                                    answerOptions = listOf(
                                        IndividualSelectionQuestion(
                                            text = "Добавить Эволокумаб 140 мг 1 раз в 2 " +
                                                    "недели",
                                            answerOptions = null
                                        )
                                    )
                                ),
                                IndividualSelectionQuestion(
                                    text = "Да",
                                    answerOptions = listOf(
                                        IndividualSelectionQuestion(
                                            text = getTextIndividualSelectionQuestion(),
                                            answerOptions = null
                                        )
                                    )
                                )
                            )
                        ),
                    )
                ),
            )
        )
    }

    private fun getTextIndividualSelectionQuestion(): String = "Продолжить назначенную терапию"

    private fun getLipidProfileResult(
        lipidRiskGroupType: LipidRiskGroupType,
    ): LipidProfileResult {
        return LipidProfileResult(
            resultTitle = getResultTitle(),
            lipidRiskGroupType = lipidRiskGroupType,
            description = getDescription(lipidRiskGroupType)
        )
    }

    private fun getResultTitle(): String = "Группа риска - "

    private fun getDescription(lipidRiskGroupType: LipidRiskGroupType): String {

        val description = when (lipidRiskGroupType) {
            LipidRiskGroupType.VERT_TALL -> {
                "рекомендуется назначение средиземноморской диеты, исследование липидного профиля " +
                        "крови, генетических маркеров семейной гиперхолестеринемии и консультация " +
                        "детского липидолога и/или детского кардиолога"
            }

            LipidRiskGroupType.HIGH -> {
                "рекомендуется назначение средиземноморской диеты, исследование липидного профиля крови " +
                        "и по их результатам консультация детского липидолога и/или детского кардиолога"
            }

            LipidRiskGroupType.MEDIUM -> {
                "рекомендуется исследование липидного профиля крови и по их результатам консультация " +
                        "детского липидолога и/или детского кардиолога"
            }

            LipidRiskGroupType.LOW -> {
                "дальнейшее наблюдение пациента в динамике"
            }
        }

        return "Рекомендации: $description"
    }
}