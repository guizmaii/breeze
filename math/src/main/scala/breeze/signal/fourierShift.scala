package breeze.signal

import breeze.generic.UFunc
import breeze.macros.expand
import breeze.linalg.DenseVector
import breeze.numerics._
import breeze.math.Complex
import breeze.storage.Zero

import scala.reflect.ClassTag

//ToDo: 2D fourierShift/iFourierShift, make horz/vert join function first

/**Shift the zero-frequency component to the center of the spectrum.
  * This function swaps half-spaces for all axes listed (defaults to all). Note that y[0] is the Nyquist component only if len(x) is even.
  *
  * @param dft input array
  * @return
  */
  object fourierShift extends UFunc {

    implicit def implFourierShift[T: Zero: ClassTag]: Impl[DenseVector[T], DenseVector[T]] = {
      new Impl[DenseVector[T], DenseVector[T]] {
        def apply(dft: DenseVector[T]): DenseVector[T] = {
          if( isEven(dft.length) ) DenseVector.vertcat( dft( dft.length/2 to -1 ), dft( 0 until dft.length / 2 ) )
          else DenseVector.vertcat( dft( (dft.length + 1)/2 to -1 ), dft( 0 to (dft.length - 1)/2 ) )
        }
      }

    }


  }
