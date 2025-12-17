package pt.iade.ei.gymbro.data.network

import com.google.gson.annotations.SerializedName


interface Dropdownable {
    val id: Int
    val label: String
}


data class LocationItem(
    @SerializedName("localizacao_id") override val id: Int,
    @SerializedName("concelho") val name: String,
    @SerializedName("distrito") val district: String?
) : Dropdownable {
    override val label: String get() = "$name, $district"
}


data class WeekdayItem(
    @SerializedName("dia_id") override val id: Int,
    @SerializedName("nome_dia") override val label: String
) : Dropdownable


data class PeriodItem(
    @SerializedName("periodo_id") override val id: Int,
    @SerializedName("periodo_nome") override val label: String
) : Dropdownable


data class WorkoutTypeItem(
    @SerializedName("tipo_treino_id") override val id: Int,
    @SerializedName("nome") override val label: String
) : Dropdownable


data class LevelItem(
    @SerializedName("nivel_id") override val id: Int,
    @SerializedName("nivel_nome") override val label: String
) : Dropdownable