package org.warthog.generic.formulas

/**
 * Copyright (c) 2011, Andreas J. Kuebler & Christoph Zengler
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * Case Class for a truth value (`true` or `false`)
 * @param tv the string representing the truth value
 *
 * Author: zengler
 * Date:   25.01.12
 */
abstract case class TruthValue[-L <: Logic](tv: String) extends Formula[L] {
  override def toString = tv

  def atoms = List()

  def vars = List()

  def freeVars = List()

  def boundVars = List()

  def numOfNodes = 1

  def numOfAtoms = 0

  def syntacticalRewrite[T <: L](subs: Map[Formula[T], Formula[T]]) = subs.get(this) match {
    case Some(p) => p
    case None    => this
  }

  def isNNF = true

  def priority = 100
}

/**
 * The constant `true`
 */
class Verum[-L <: Logic] extends TruthValue[L](Formula.TRUE) {
  def getNNF(phase: Boolean) = if (phase) this else Falsum()
}

object Verum {
  def apply[L <: Logic]() = new Verum[L]()
}

/**
 * The constant `false`
 */
class Falsum[-L <: Logic] extends TruthValue[L](Formula.FALSE) {
  def getNNF(phase: Boolean) = if (phase) this else Verum()
}

object Falsum {
  def apply[L <: Logic]() = new Falsum[L]()
}
