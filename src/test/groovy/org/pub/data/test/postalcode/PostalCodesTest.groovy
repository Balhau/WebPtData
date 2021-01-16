package org.pub.data.test.postalcode

import org.pub.pt.data.sources.postalcode.PostalCodes
import org.pub.pt.data.sources.postalcode.domain.PostalCode
import spock.lang.Specification

class PostalCodesTest extends Specification {

    private PostalCodes postalCodes

    def setup() {
        postalCodes = new PostalCodes()
    }

    def "Get postal code by location"() {
        when:
        "We have a location"
        String location = "Porto"
        "And get post codes by it"
        List<PostalCode> codes = postalCodes.getPostalCodesByLocal(null, location)
        then:
        "Then a not empty list of PostalCode objects should be returned"
        codes != null && codes.size() > 0
    }

    def "Get postal code by street"() {
        when:
        "We have a street"
        String street = "Rua Padre Costa"
        "And get post codes by it"
        List<PostalCode> codes = postalCodes.getPostalCodesByLocal(street, null)
        then:
        "Then a not empty list of PostalCode objects should be returned"
        codes != null && codes.size() > 0
    }

    def "Get postal code by major code"() {
        when:
        "We have major code"
        String major = "4410"
        String minor = null
        "And get post codes by them"
        List<PostalCode> codes = postalCodes.getPostalCodesByCode(major, minor)
        then:
        "Then a not empty list of PostalCode objects should be returned"
        codes != null && codes.size() > 0
    }

    def "Get postal code by major and minor codes"() {
        when:
        "We have a major and a minor postal codes"
        String major = "4410"
        String minor = "491"
        "And get post codes by them"
        List<PostalCode> codes = postalCodes.getPostalCodesByCode(major, minor)
        then:
        "Then a not empty list of PostalCode objects should be returned"
        codes != null && codes.size() > 0
    }

    def "Exception should be thrown when invalid codes are provided"() {
        when:
        "We have a major and a minor postal codes"
        String major = "491"
        String minor = "4410491"
        "And get post codes by them"
        List<PostalCode> codes = postalCodes.getPostalCodesByCode(major, minor)
        then:
        "Then an IndexOutOfBoundsException should be thrown"
        thrown(IndexOutOfBoundsException)
    }

    def "Exception should be thrown when location is invalid"() {
        when:
        "We have a major and a minor postal codes"
        String invalidLocation = "invalidLocation"
        "And get post codes by them"
        List<PostalCode> codes = postalCodes.getPostalCodesByLocal(null, invalidLocation);
        then:
        "Then an IndexOutOfBoundsException should be thrown"
        thrown(IndexOutOfBoundsException)
    }

    def "Exception should be thrown when street is invalid"() {
        when:
        "We have a major and a minor postal codes"
        String invalidStreet = "invalidStreet"
        "And get post codes by them"
        List<PostalCode> codes = postalCodes.getPostalCodesByLocal(invalidStreet, null);
        then:
        "Then an IndexOutOfBoundsException should be thrown"
        thrown(IndexOutOfBoundsException)
    }
}