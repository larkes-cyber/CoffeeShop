package com.example.coffeeshop.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LocationDto(

	@SerialName("response")
	val response: Response? = null
)

@Serializable
data class Premise(

	@SerialName("PremiseName")
	val premiseName: String? = null,

	@SerialName("PremiseNumber")
	val premiseNumber: String? = null,

	@SerialName("PostalCode")
	val postalCode: PostalCode? = null
)

@Serializable
data class AdministrativeArea(

	@SerialName("AdministrativeAreaName")
	val administrativeAreaName: String? = null,

	@SerialName("Locality")
	val locality: Locality? = null
)

@Serializable
@SerialName("GeocoderMetaData")
data class GeocoderMetaData(

	@SerialName("Address")
	val address: Address? = null,

	@SerialName("AddressDetails")
	val addressDetails: AddressDetails? = null,

	@SerialName("kind")
	val kind: String? = null,

	@SerialName("precision")
	val precision: String? = null,

	@SerialName("text")
	val text: String? = null
)

@Serializable
data class GeoObjectCollection(

	@SerialName("metaDataProperty")
	val metaDataProperty: MetaDataProperty? = null,

	@SerialName("featureMember")
	val featureMember: List<FeatureMemberItem?>? = null
)

@Serializable
data class MetaDataProperty(

	@SerialName("GeocoderResponseMetaData")
	val geocoderResponseMetaData: GeocoderResponseMetaData? = null,

	@SerialName("GeocoderMetaData")
	val geocoderMetaData: GeocoderMetaData? = null
)

@Serializable
data class Locality(

	@SerialName("LocalityName")
	val localityName: String? = null,

	@SerialName("Premise")
	val premise: Premise? = null,

	@SerialName("DependentLocality")
	val dependentLocality: DependentLocality? = null,

	@SerialName("Thoroughfare")
	val thoroughfare: Thoroughfare? = null
)

@Serializable
data class GeocoderResponseMetaData(

	@SerialName("request")
	val request: String? = null,

	@SerialName("found")
	val found: String? = null,

	@SerialName("Point")
	val point: Point? = null,

	@SerialName("results")
	val results: String? = null
)

@Serializable
data class DependentLocality(

	@SerialName("DependentLocalityName")
	val dependentLocalityName: String? = null,

	@SerialName("DependentLocality")
	val dependentLocality: DependentLocality? = null
)

@Serializable
data class GeoObject(

	@SerialName("metaDataProperty")
	val metaDataProperty: MetaDataProperty? = null,

	@SerialName("boundedBy")
	val boundedBy: BoundedBy? = null,

	@SerialName("name")
	val name: String? = null,

	@SerialName("Point")
	val point: Point? = null,

	@SerialName("uri")
	val uri: String? = null,

	@SerialName("description")
	val description: String? = null
)

@Serializable
data class Thoroughfare(

	@SerialName("ThoroughfareName")
	val thoroughfareName: String? = null,

	@SerialName("Premise")
	val premise: Premise? = null
)

@Serializable
data class BoundedBy(

	@SerialName("Envelope")
	val envelope: Envelope? = null
)

@Serializable
data class Point(

	@SerialName("pos")
	val pos: String? = null
)

@Serializable
data class Country(

	@SerialName("AdministrativeArea")
	val administrativeArea: AdministrativeArea? = null,

	@SerialName("CountryName")
	val countryName: String? = null,

	@SerialName("AddressLine")
	val addressLine: String? = null,

	@SerialName("CountryNameCode")
	val countryNameCode: String? = null
)

@Serializable
data class Envelope(

	@SerialName("lowerCorner")
	val lowerCorner: String? = null,

	@SerialName("upperCorner")
	val upperCorner: String? = null
)

@Serializable
data class ComponentsItem(

	@SerialName("kind")
	val kind: String? = null,

	@SerialName("name")
	val name: String? = null
)

@Serializable
data class Address(

	@SerialName("Components")
	val components: List<ComponentsItem?>? = null,

	@SerialName("formatted")
	val formatted: String? = null,

	@SerialName("country_code")
	val countryCode: String? = null,

	@SerialName("postal_code")
	val postalCode: String? = null
)

@Serializable
data class PostalCode(

	@SerialName("PostalCodeNumber")
	val postalCodeNumber: String? = null
)

@Serializable
data class Response(

	@SerialName("GeoObjectCollection")
	val geoObjectCollection: GeoObjectCollection? = null
)

@Serializable
data class AddressDetails(

	@SerialName("Address")
	val address: String? = null,

	@SerialName("Country")
	val country: Country? = null
)

@Serializable
data class FeatureMemberItem(

	@SerialName("GeoObject")
	val geoObject: GeoObject? = null
)
