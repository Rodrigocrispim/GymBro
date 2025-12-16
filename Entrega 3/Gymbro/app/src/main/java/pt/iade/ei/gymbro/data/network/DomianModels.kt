package pt.iade.ei.gymbro.data.network

import com.google.gson.annotations.SerializedName

// Interface para facilitar o uso nos Dropdowns do Jetpack Compose
interface Dropdownable {
    val id: Int
    val label: String
}

// 1. Localizações (JSON: localizacao_id, concelho)
data class LocationItem(
    @SerializedName("localizacao_id") override val id: Int,
    @SerializedName("concelho") val name: String,
    @SerializedName("distrito") val district: String?
) : Dropdownable {
    override val label: String get() = "$name, $district"
}

// 2. Dias da Semana (JSON: dia_id, nome_dia)
data class WeekdayItem(
    @SerializedName("dia_id") override val id: Int,
    @SerializedName("nome_dia") override val label: String
) : Dropdownable

// 3. Períodos do Dia (JSON: periodo_id, periodo_nome)
data class PeriodItem(
    @SerializedName("periodo_id") override val id: Int,
    @SerializedName("periodo_nome") override val label: String
) : Dropdownable

// 4. Tipos de Treino (JSON: tipo_treino_id, nome)
data class WorkoutTypeItem(
    @SerializedName("tipo_treino_id") override val id: Int,
    @SerializedName("nome") override val label: String
) : Dropdownable

// 5. Níveis de Treino (JSON: nivel_id, nivel_nome)
data class LevelItem(
    @SerializedName("nivel_id") override val id: Int,
    @SerializedName("nivel_nome") override val label: String
) : Dropdownable