/*
 * Copyright (c) 2015 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.common.truth;

import static com.google.common.truth.Truth.assertThat;
import static java.math.BigDecimal.TEN;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;

/**
 * Tests for BigDecimal Subjects.
 *
 * @author Kurt Alfred Kluever
 */
@RunWith(JUnit4.class)
public class BigDecimalTest {

  @Test public void isEqualTo() {
    // make sure this still works
    assertThat(TEN).isEqualTo(TEN);
  }

  @Test public void isEquivalentAccordingToCompareTo() {
    // make sure this still works
    assertThat(TEN).isEquivalentAccordingToCompareTo(TEN);
  }

  @Test public void isEqualToIgnoringScale_bigDecimal() {
    assertThat(TEN).isEqualToIgnoringScale(TEN);
    assertThat(TEN).isEqualToIgnoringScale(new BigDecimal(10));
    try {
      assertThat(TEN).isEqualToIgnoringScale(new BigDecimal(3));
      fail("should have thrown");
    } catch (AssertionError e) {
      assertThat(e).hasMessage("<10> should have had the same value as <3> (scale is ignored)");
    }
  }

  @Test public void isEqualToIgnoringScale_int() {
    assertThat(TEN).isEqualToIgnoringScale(10);
    try {
      assertThat(TEN).isEqualToIgnoringScale(3);
      fail("should have thrown");
    } catch (AssertionError e) {
      assertThat(e).hasMessage("<10> should have had the same value as <3> (scale is ignored)");
    }
  }

  @Test public void isEqualToIgnoringScale_long() {
    assertThat(TEN).isEqualToIgnoringScale(10L);
    try {
      assertThat(TEN).isEqualToIgnoringScale(3L);
      fail("should have thrown");
    } catch (AssertionError e) {
      assertThat(e).hasMessage("<10> should have had the same value as <3> (scale is ignored)");
    }
  }

  @Test public void isEqualToIgnoringScale_string() {
    assertThat(TEN).isEqualToIgnoringScale("10");
    assertThat(TEN).isEqualToIgnoringScale("10.");
    assertThat(TEN).isEqualToIgnoringScale("10.0");
    assertThat(TEN).isEqualToIgnoringScale("10.00");
    try {
      assertThat(TEN).isEqualToIgnoringScale("3");
      fail("should have thrown");
    } catch (AssertionError e) {
      assertThat(e).hasMessage("<10> should have had the same value as <3> (scale is ignored)");
    }
  }
}
