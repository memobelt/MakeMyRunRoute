package memo.makemyrunroute.network

data class NearestRoadsResponse(val snappedPoints: List<SnappedPoints>)

data class SnappedPoints(
        val location: Location,
        val originalIndex: Int?,
        val placeId: String?
)

data class Location(
        val latitude: String?,
        val longitude: String?
)

data class MapsResponse(val result: StreetResult)

data class StreetResult(val address_components: List<AddressComponents>)

data class AddressComponents(
        val long_name: String,
        val short_name: String,
        val types: List<String>
) {
    val STREET_NUMBER = 0
    val ROUTE = 1
}