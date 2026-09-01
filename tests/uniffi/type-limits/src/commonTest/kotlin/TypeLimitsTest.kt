/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.*
import kotlinx.coroutines.test.runTest
import type_limits.*
import kotlin.test.*

class TypeLimitsTest {
    @Test
    fun testUnsignedDirectReturnLimits() {
        outputU8Max() shouldBe UByte.MAX_VALUE
        outputU16Max() shouldBe UShort.MAX_VALUE
        outputCustomU8Max() shouldBe UByte.MAX_VALUE
        outputCustomU16Max() shouldBe UShort.MAX_VALUE
        takeU8(UByte.MAX_VALUE) shouldBe UByte.MAX_VALUE
        takeU16(UShort.MAX_VALUE) shouldBe UShort.MAX_VALUE
    }

    @Test
    fun testCustomUnsignedAsyncDirectReturnLimits() = runTest {
        outputCustomU8MaxAsync() shouldBe UByte.MAX_VALUE
        outputCustomU16MaxAsync() shouldBe UShort.MAX_VALUE
    }

    @Test
    fun testStringLimits() {
        shouldThrow<CharacterCodingException> {
            takeString("\ud800")
        }
        takeString("") shouldBe ""
        takeString("愛") shouldBe "愛"
        takeString("💖") shouldBe "💖"
    }
}
