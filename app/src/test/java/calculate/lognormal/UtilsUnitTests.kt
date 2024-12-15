package calculate.lognormal

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import java.security.InvalidParameterException

class UtilsUnitTests {
    @Test
    fun check_is_empty() {
        try {
            Utils.validateParameters("", "")
            Assert.fail("Should have thrown InvalidParameterException exception")
        } catch (e: InvalidParameterException) {
            assertEquals("Заполните все поля", e.message)
        }
    }

    @Test
    fun check_sigma_is_zero() {
        try {
            Utils.validateParameters("123", "0.0")
            Assert.fail("Should have thrown InvalidParameterException exception")
        } catch (e: InvalidParameterException) {
            assertEquals("Параметр σ²>0", e.message)
        }
    }
}
