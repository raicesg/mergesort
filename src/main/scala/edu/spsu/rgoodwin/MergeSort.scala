package edu.spsu.rgoodwin

class MergeSort {
  // Class Variables
  var compareCount = 0

  // Mergesort Algorithm
  def mergesort(n: Int, S: Array[Int]): Unit = {
    if (n > 1) {
      val hSize: Int = n/2
      val mSize: Int = n - hSize
      val Ums = new Array[Int](hSize);
	    val Vms = new Array[Int](mSize);
      var vmsCount = 0

      // Copy S[0] though S[h] to U[0] to U[h]
      for (count <- 0 until hSize)
        Ums(count) = S(count)

      // Copy S[h] though S[n] to V[0] to V[m]
      for (count <- hSize until n) {
        Vms(vmsCount) = S(count)
        vmsCount += 1
      }  

      // Sort Map recursively with mergesort algorithm
      mergesort(hSize, Ums)
      mergesort(mSize, Vms)

      // Merge the two sorted maps
      merge(hSize, mSize, Ums, Vms, S)
    }
  }

  // Merge Algorithm
  def merge(h: Int, m: Int, U: Array[Int], V: Array[Int], S: Array[Int]) {
    // Index Values
    var i = 0
    var j = 0
    var k = 0

    while (i < h && j < m) {
      if (U(i) < V(j)) {
        S(k) = U(i)
        i += 1
        compareCount += 1
      }
      else {
        S(k) = V(j)
        j += 1
        compareCount += 1
      }
      k += 1
    }
    if (i > h) {
      // Copy V[j] though V[m] to S[k] through S[h+m]
      for (count <- j until m) {
        S(k) = V(count)
        k += 1
      }
    }
    else {
      // Copy U[i] though U[h] to S[k] through S[h+m]
      for (count <- i until h) {
        S(k) = U(count)
        k += 1
      }
    }
  }
}