package edu.spsu.rgoodwin

/**
 * Course:        CS4413
 * Student Name:  Raices Goodwin
 * Student ID:    000060962
 * Assignment #:  #1
 * Due Date:      10/03/2011
 *
 * Signature:     ___________________
 *
 * Score:         ___________________
 */

import swing._
import event._

object App extends SimpleSwingApplication {
  def top = new MainFrame {
    title = "Mergesort"

    // GUI
    val ui = new BorderPanel {
      // GUI Global Values
      var msValue = new Array[Int](0)
      var msAnswer = new MergeSort
      var i: Int = 1
      var j: Int = 5
      var rnd = new scala.util.Random
      var range = i to j

      // North Layout Global Content
      object sortSize extends TextField { columns = 5 }

      // West Layout Global Content
      val Button1 = new Button("Increasing order")
      val Button2 = new Button("Decreasing order")
      val Button3 = new Button("Random order")

      // Center Layout Global Content
      object generatedValue extends TextField { columns = 25 }
      object sortedValue extends TextField { columns = 25 }
      object keysCompared extends TextField { columns = 25 }

      // GUI Layout
      import BorderPanel.Position._
      layout {
        new FlowPanel {
          contents += new Label("Please enter Sort Size ")
          contents += sortSize
        }
      } = North
      layout {
        new GridPanel(3,1) {
          contents += Button1
          contents += Button2
          contents += Button3
        }
      } = West
      layout {
        new GridPanel(6,1) {
          contents += new Label("Generated Value")
          contents += generatedValue
          contents += new Label("Sorted Value")
          contents += sortedValue
          contents += new Label("Keys Compared In Sort")
          contents += keysCompared
          border = Swing.EmptyBorder(30, 30, 30, 30)
        }
      } = Center

      // Content listeners and reactors
      listenTo(sortSize, Button1, Button2, Button3)
      reactions += {
        // Increasing order button reactions
        case ButtonClicked(Button1) =>
          // Initialize sort array
          msValue = new Array[Int](sortSize.text.toInt)
          for (count <- 0 until sortSize.text.toInt) {
            range = i to j
            msValue(count) = range(rnd.nextInt(range length))
            i = msValue(count)
            j = i + 5
          }
          // Display generated value
          generatedValue.text = msValue.mkString("", " , ", "")
          // Call Mergesort Algorithm
          msAnswer.mergesort(sortSize.text.toInt, msValue)
          // Display sorted value and number of compared keys
          sortedValue.text = msValue.mkString("", " , ", "")
          keysCompared.text = msAnswer.compareCount.toString
        
        // Decreasing order button reactions
        case ButtonClicked(Button2) =>
          // Initialize sort array
          msValue = new Array[Int](sortSize.text.toInt)
          for (count <- 0 until sortSize.text.toInt) {
            range = i to j
            msValue(count) = range(rnd.nextInt(range length))
            i = msValue(count)
            j = i + 5
          }
          // Display generated value
          generatedValue.text = msValue.reverse.mkString("", " , ", "")
          // Call Mergesort Alogorithm
          msAnswer.mergesort(sortSize.text.toInt, msValue.reverse)
          // Display sorted value and number of compared keys
          sortedValue.text = msValue.mkString("", " , ", "")
          keysCompared.text = msAnswer.compareCount.toString

        // Random order button reactions
        case ButtonClicked(Button3) =>
          // Initialize sort array
          msValue = new Array[Int](sortSize.text.toInt)
          for (count <- 0 until sortSize.text.toInt) {
            range = 1 to 50
            msValue(count) = range(rnd.nextInt(range length))
          }
          // Display generated value
          generatedValue.text = msValue.mkString("", " , ", "")
          // Call Mergesort Alogorithm
          msAnswer.mergesort(sortSize.text.toInt, msValue)
          // Display sorted value and number of compared keys
          sortedValue.text = msValue.mkString("", " , ", "")
          keysCompared.text = msAnswer.compareCount.toString
      }
    }
    
    // Mainframe Layout
    contents = ui
  }
}