{:namespaces
 ({:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj",
   :wiki-url "http://clojure.github.com/clojure/clojure.core-api.html",
   :name "clojure.core",
   :doc "Fundamental library of the Clojure language"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/inspector.clj",
   :wiki-url
   "http://clojure.github.com/clojure/clojure.inspector-api.html",
   :name "clojure.inspector",
   :author "Rich Hickey",
   :doc "Graphical object inspector for Clojure data structures."}
  {:source-url
   "http://github.com/clojure/clojure/blob/2cc710e7aeaab08e0739debe21e2cc6b7020e1b1/src/clj/clojure/main.clj",
   :wiki-url "http://clojure.github.com/clojure/clojure.main-api.html",
   :name "clojure.main",
   :doc nil}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/set.clj",
   :wiki-url "http://clojure.github.com/clojure/clojure.set-api.html",
   :name "clojure.set",
   :author "Rich Hickey",
   :doc "Set operations such as union/intersection."}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/stacktrace.clj",
   :wiki-url
   "http://clojure.github.com/clojure/clojure.stacktrace-api.html",
   :name "clojure.stacktrace",
   :author "Stuart Sierra",
   :doc "Print stack traces oriented towards Clojure, not Java."}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/template.clj",
   :wiki-url
   "http://clojure.github.com/clojure/clojure.template-api.html",
   :name "clojure.template",
   :author "Stuart Sierra",
   :doc
   "Macros that expand to repeated copies of a template expression."}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj",
   :wiki-url "http://clojure.github.com/clojure/clojure.test-api.html",
   :name "clojure.test",
   :author
   "Stuart Sierra, with contributions and suggestions by \n  Chas Emerick, Allen Rohner, and Stuart Halloway",
   :doc
   "A unit testing framework.\n\nASSERTIONS\n\nThe core of the library is the \"is\" macro, which lets you make\nassertions of any arbitrary expression:\n\n(is (= 4 (+ 2 2)))\n(is (instance? Integer 256))\n(is (.startsWith \"abcde\" \"ab\"))\n\nYou can type an \"is\" expression directly at the REPL, which will\nprint a message if it fails.\n\n    user> (is (= 5 (+ 2 2)))\n\n    FAIL in  (:1)\n    expected: (= 5 (+ 2 2))\n      actual: (not (= 5 4))\n    false\n\nThe \"expected:\" line shows you the original expression, and the\n\"actual:\" shows you what actually happened.  In this case, it\nshows that (+ 2 2) returned 4, which is not = to 5.  Finally, the\n\"false\" on the last line is the value returned from the\nexpression.  The \"is\" macro always returns the result of the\ninner expression.\n\nThere are two special assertions for testing exceptions.  The\n\"(is (thrown? c ...))\" form tests if an exception of class c is\nthrown:\n\n(is (thrown? ArithmeticException (/ 1 0))) \n\n\"(is (thrown-with-msg? c re ...))\" does the same thing and also\ntests that the message on the exception matches the regular\nexpression re:\n\n(is (thrown-with-msg? ArithmeticException #\"Divide by zero\"\n                      (/ 1 0)))\n\nDOCUMENTING TESTS\n\n\"is\" takes an optional second argument, a string describing the\nassertion.  This message will be included in the error report.\n\n(is (= 5 (+ 2 2)) \"Crazy arithmetic\")\n\nIn addition, you can document groups of assertions with the\n\"testing\" macro, which takes a string followed by any number of\nassertions.  The string will be included in failure reports.\nCalls to \"testing\" may be nested, and all of the strings will be\njoined together with spaces in the final report, in a style\nsimilar to RSpec <http://rspec.info/>\n\n(testing \"Arithmetic\"\n  (testing \"with positive integers\"\n    (is (= 4 (+ 2 2)))\n    (is (= 7 (+ 3 4))))\n  (testing \"with negative integers\"\n    (is (= -4 (+ -2 -2)))\n    (is (= -1 (+ 3 -4)))))\n\nNote that, unlike RSpec, the \"testing\" macro may only be used\nINSIDE a \"deftest\" or \"with-test\" form (see below).\n\n\nDEFINING TESTS\n\nThere are two ways to define tests.  The \"with-test\" macro takes\na defn or def form as its first argument, followed by any number\nof assertions.  The tests will be stored as metadata on the\ndefinition.\n\n(with-test\n    (defn my-function [x y]\n      (+ x y))\n  (is (= 4 (my-function 2 2)))\n  (is (= 7 (my-function 3 4))))\n\nAs of Clojure SVN rev. 1221, this does not work with defmacro.\nSee http://code.google.com/p/clojure/issues/detail?id=51\n\nThe other way lets you define tests separately from the rest of\nyour code, even in a different namespace:\n\n(deftest addition\n  (is (= 4 (+ 2 2)))\n  (is (= 7 (+ 3 4))))\n\n(deftest subtraction\n  (is (= 1 (- 4 3)))\n  (is (= 3 (- 7 4))))\n\nThis creates functions named \"addition\" and \"subtraction\", which\ncan be called like any other function.  Therefore, tests can be\ngrouped and composed, in a style similar to the test framework in\nPeter Seibel's \"Practical Common Lisp\"\n<http://www.gigamonkeys.com/book/practical-building-a-unit-test-framework.html>\n\n(deftest arithmetic\n  (addition)\n  (subtraction))\n\nThe names of the nested tests will be joined in a list, like\n\"(arithmetic addition)\", in failure reports.  You can use nested\ntests to set up a context shared by several tests.\n\n\nRUNNING TESTS\n\nRun tests with the function \"(run-tests namespaces...)\":\n\n(run-tests 'your.namespace 'some.other.namespace)\n\nIf you don't specify any namespaces, the current namespace is\nused.  To run all tests in all namespaces, use \"(run-all-tests)\".\n\nBy default, these functions will search for all tests defined in\na namespace and run them in an undefined order.  However, if you\nare composing tests, as in the \"arithmetic\" example above, you\nprobably do not want the \"addition\" and \"subtraction\" tests run\nseparately.  In that case, you must define a special function\nnamed \"test-ns-hook\" that runs your tests in the correct order:\n\n(defn test-ns-hook []\n  (arithmetic))\n\n\nOMITTING TESTS FROM PRODUCTION CODE\n\nYou can bind the variable \"*load-tests*\" to false when loading or\ncompiling code in production.  This will prevent any tests from\nbeing created by \"with-test\" or \"deftest\".\n\n\nFIXTURES (new)\n\nFixtures allow you to run code before and after tests, to set up\nthe context in which tests should be run.\n\nA fixture is just a function that calls another function passed as\nan argument.  It looks like this:\n\n(defn my-fixture [f]\n   Perform setup, establish bindings, whatever.\n  (f)  Then call the function we were passed.\n   Tear-down / clean-up code here.\n )\n\nFixtures are attached to namespaces in one of two ways.  \"each\"\nfixtures are run repeatedly, once for each test function created\nwith \"deftest\" or \"with-test\".  \"each\" fixtures are useful for\nestablishing a consistent before/after state for each test, like\nclearing out database tables.\n\n\"each\" fixtures can be attached to the current namespace like this:\n(use-fixtures :each fixture1 fixture2 ...)\nThe fixture1, fixture2 are just functions like the example above.\nThey can also be anonymous functions, like this:\n(use-fixtures :each (fn [f] setup... (f) cleanup...))\n\nThe other kind of fixture, a \"once\" fixture, is only run once,\naround ALL the tests in the namespace.  \"once\" fixtures are useful\nfor tasks that only need to be performed once, like establishing\ndatabase connections, or for time-consuming tasks.\n\nAttach \"once\" fixtures to the current namespace like this:\n(use-fixtures :once fixture1 fixture2 ...)\n\n\nSAVING TEST OUTPUT TO A FILE\n\nAll the test reporting functions write to the var *test-out*.  By\ndefault, this is the same as *out*, but you can rebind it to any\nPrintWriter.  For example, it could be a file opened with\nclojure.contrib.duck-streams/writer.\n\n\nEXTENDING TEST-IS (ADVANCED)\n\nYou can extend the behavior of the \"is\" macro by defining new\nmethods for the \"assert-expr\" multimethod.  These methods are\ncalled during expansion of the \"is\" macro, so they should return\nquoted forms to be evaluated.\n\nYou can plug in your own test-reporting framework by rebinding\nthe \"report\" function: (report event)\n\nThe 'event' argument is a map.  It will always have a :type key,\nwhose value will be a keyword signaling the type of event being\nreported.  Standard events with :type value of :pass, :fail, and\n:error are called when an assertion passes, fails, and throws an\nexception, respectively.  In that case, the event will also have\nthe following keys:\n\n  :expected   The form that was expected to be true\n  :actual     A form representing what actually occurred\n  :message    The string message given as an argument to 'is'\n\nThe \"testing\" strings will be a list in \"*testing-contexts*\", and\nthe vars being tested will be a list in \"*testing-vars*\".\n\nYour \"report\" function should wrap any printing calls in the\n\"with-test-out\" macro, which rebinds *out* to the current value\nof *test-out*.\n\nFor additional event types, see the examples in the code."}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/walk.clj",
   :wiki-url "http://clojure.github.com/clojure/clojure.walk-api.html",
   :name "clojure.walk",
   :author "Stuart Sierra",
   :doc
   "This file defines a generic tree walker for Clojure data\nstructures.  It takes any data structure (list, vector, map, set,\nseq), calls a function on every element, and uses the return value\nof the function in place of the original.  This makes it fairly\neasy to write recursive search-and-replace functions, as shown in\nthe examples.\n\nNote: \"walk\" supports all Clojure data structures EXCEPT maps\ncreated with sorted-map-by.  There is no (obvious) way to retrieve\nthe sorting function."}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/xml.clj",
   :wiki-url "http://clojure.github.com/clojure/clojure.xml-api.html",
   :name "clojure.xml",
   :author "Rich Hickey",
   :doc "XML reading/writing."}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj",
   :wiki-url "http://clojure.github.com/clojure/clojure.zip-api.html",
   :name "clojure.zip",
   :author "Rich Hickey",
   :doc
   "Functional hierarchical zipper, with navigation, editing, \nand enumeration.  See Huet"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/test/junit.clj",
   :wiki-url
   "http://clojure.github.com/clojure/clojure.test.junit-api.html",
   :name "clojure.test.junit",
   :author "Jason Sankey",
   :doc
   "clojure.test extension for JUnit-compatible XML output.\n\nJUnit (http://junit.org/) is the most popular unit-testing library\nfor Java.  As such, tool support for JUnit output formats is\ncommon.  By producing compatible output from tests, this tool\nsupport can be exploited.\n\nTo use, wrap any calls to clojure.test/run-tests in the\nwith-junit-output macro, like this:\n\n  (use 'clojure.test)\n  (use 'clojure.contrib.test.junit)\n\n  (with-junit-output\n    (run-tests 'my.cool.library))\n\nTo write the output to a file, rebind clojure.test/*test-out* to\nyour own PrintWriter (perhaps opened using\nclojure.contrib.duck-streams/writer)."}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/test/tap.clj",
   :wiki-url
   "http://clojure.github.com/clojure/clojure.test.tap-api.html",
   :name "clojure.test.tap",
   :author "Stuart Sierra",
   :doc
   "clojure.test extensions for the Test Anything Protocol (TAP)\n\nTAP is a simple text-based syntax for reporting test results.  TAP\nwas originally develped for Perl, and now has implementations in\nseveral languages.  For more information on TAP, see\nhttp://testanything.org/ and\nhttp://search.cpan.org/~petdance/TAP-1.0.0/TAP.pm\n\nTo use this library, wrap any calls to\nclojure.test/run-tests in the with-tap-output macro,\nlike this:\n\n  (use 'clojure.test)\n  (use 'clojure.test.tap)\n\n  (with-tap-output\n   (run-tests 'my.cool.library))"}),
 :vars
 ({:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L687",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/*",
   :namespace "clojure.core",
   :line 687,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([] [x] [x y] [x y & more]),
   :doc "Returns the product of nums. (*) returns 1.",
   :name "*"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4259",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/*1",
   :namespace "clojure.core",
   :line 4259,
   :file "clojure/core.clj",
   :var-type "var",
   :doc "bound in a repl thread to the most recent value printed",
   :name "*1"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4263",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/*2",
   :namespace "clojure.core",
   :line 4263,
   :file "clojure/core.clj",
   :var-type "var",
   :doc
   "bound in a repl thread to the second most recent value printed",
   :name "*2"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4267",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/*3",
   :namespace "clojure.core",
   :line 4267,
   :file "clojure/core.clj",
   :var-type "var",
   :doc
   "bound in a repl thread to the third most recent value printed",
   :name "*3"}
  {:source-url nil,
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/*agent*",
   :namespace "clojure.core",
   :var-type "var",
   :doc
   "The agent currently running an action on this thread, else nil",
   :name "*agent*"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4552",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/*clojure-version*",
   :namespace "clojure.core",
   :line 4552,
   :file "clojure/core.clj",
   :var-type "var",
   :doc
   "The version info for Clojure core, as a map containing :major :minor \n:incremental and :qualifier keys. Feature releases may increment \n:minor and/or :major, bugfix releases will increment :incremental. \nPossible values of :qualifier include \"GA\", \"SNAPSHOT\", \"RC-x\" \"BETA-x\"",
   :name "*clojure-version*"}
  {:source-url nil,
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/*command-line-args*",
   :namespace "clojure.core",
   :var-type "var",
   :doc
   "A sequence of the supplied command line arguments, or nil if\nnone were supplied",
   :name "*command-line-args*"}
  {:source-url nil,
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/*compile-files*",
   :namespace "clojure.core",
   :var-type "var",
   :doc "Set to true when compiling files, false otherwise.",
   :name "*compile-files*"}
  {:source-url nil,
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/*compile-path*",
   :namespace "clojure.core",
   :var-type "var",
   :doc
   "Specifies the directory where 'compile' will write out .class\nfiles. This directory must be in the classpath for 'compile' to\nwork.\n\nDefaults to \"classes\"",
   :name "*compile-path*"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4271",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/*e",
   :namespace "clojure.core",
   :line 4271,
   :file "clojure/core.clj",
   :var-type "var",
   :doc
   "bound in a repl thread to the most recent exception caught by the repl",
   :name "*e"}
  {:source-url nil,
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/*err*",
   :namespace "clojure.core",
   :var-type "var",
   :doc
   "A java.io.Writer object representing standard error for print operations.\n\nDefaults to System/err, wrapped in a PrintWriter",
   :name "*err*"}
  {:source-url nil,
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/*file*",
   :namespace "clojure.core",
   :var-type "var",
   :doc
   "The path of the file being evaluated, as a String.\n\nEvaluates to nil when there is no file, eg. in the REPL.",
   :name "*file*"}
  {:source-url nil,
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/*flush-on-newline*",
   :namespace "clojure.core",
   :var-type "var",
   :doc
   "When set to true, output will be flushed whenever a newline is printed.\n\nDefaults to true.",
   :name "*flush-on-newline*"}
  {:source-url nil,
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/*in*",
   :namespace "clojure.core",
   :var-type "var",
   :doc
   "A java.io.Reader object representing standard input for read operations.\n\nDefaults to System/in, wrapped in a LineNumberingPushbackReader",
   :name "*in*"}
  {:source-url nil,
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/*ns*",
   :namespace "clojure.core",
   :var-type "var",
   :doc
   "A clojure.lang.Namespace object representing the current namespace.",
   :name "*ns*"}
  {:source-url nil,
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/*out*",
   :namespace "clojure.core",
   :var-type "var",
   :doc
   "A java.io.Writer object representing standard output for print operations.\n\nDefaults to System/out",
   :name "*out*"}
  {:source-url nil,
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/*print-dup*",
   :namespace "clojure.core",
   :var-type "var",
   :doc
   "When set to logical true, objects will be printed in a way that preserves\ntheir type when read in later.\n\nDefaults to false.",
   :name "*print-dup*"}
  {:source-url
   "http://github.com/clojure/clojure/blob/1a0e23d0e78ef3d3a3a6267a68adcfc414d3fb56/src/clj/clojure/core_print.clj#L15",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/*print-length*",
   :namespace "clojure.core",
   :line 15,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/core_print.clj",
   :var-type "var",
   :doc
   "*print-length* controls how many items of each collection the\nprinter will print. If it is bound to logical false, there is no\nlimit. Otherwise, it must be bound to an integer indicating the maximum\nnumber of items of each collection to print. If a collection contains\nmore items, the printer will print items up to the limit followed by\n'...' to represent the remaining items. The root binding is nil\nindicating no limit.",
   :name "*print-length*"}
  {:source-url
   "http://github.com/clojure/clojure/blob/1a0e23d0e78ef3d3a3a6267a68adcfc414d3fb56/src/clj/clojure/core_print.clj#L25",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/*print-level*",
   :namespace "clojure.core",
   :line 25,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/core_print.clj",
   :var-type "var",
   :doc
   "*print-level* controls how many levels deep the printer will\nprint nested objects. If it is bound to logical false, there is no\nlimit. Otherwise, it must be bound to an integer indicating the maximum\nlevel to print. Each argument to print is at level 0; if an argument is a\ncollection, its items are at level 1; and so on. If an object is a\ncollection and is at a level greater than or equal to the value bound to\n*print-level*, the printer prints '#' to represent it. The root binding\nis nil indicating no limit.",
   :name "*print-level*"}
  {:source-url nil,
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/*print-meta*",
   :namespace "clojure.core",
   :var-type "var",
   :doc
   "If set to logical true, when printing an object, its metadata will also\nbe printed in a form that can be read back by the reader.\n\nDefaults to false.",
   :name "*print-meta*"}
  {:source-url nil,
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/*print-readably*",
   :namespace "clojure.core",
   :var-type "var",
   :doc
   "When set to logical false, strings and characters will be printed with\nnon-alphanumeric characters converted to the appropriate escape sequences.\n\nDefaults to true",
   :name "*print-readably*"}
  {:source-url nil,
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/*read-eval*",
   :namespace "clojure.core",
   :var-type "var",
   :doc
   "When set to logical false, the EvalReader (#=(...)) is disabled in the \nread/load in the thread-local binding.\nExample: (binding [*read-eval* false] (read-string \"#=(eval (def x 3))\"))\n\nDefaults to true",
   :name "*read-eval*"}
  {:source-url nil,
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/*warn-on-reflection*",
   :namespace "clojure.core",
   :var-type "var",
   :doc
   "When set to true, the compiler will emit warnings when reflection is\nneeded to resolve Java method calls or field accesses.\n\nDefaults to false.",
   :name "*warn-on-reflection*"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L677",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/+",
   :namespace "clojure.core",
   :line 677,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([] [x] [x y] [x y & more]),
   :doc "Returns the sum of nums. (+) returns 0.",
   :name "+"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L707",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/-",
   :namespace "clojure.core",
   :line 707,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x] [x y] [x y & more]),
   :doc
   "If no ys are supplied, returns the negation of x, else subtracts\nthe ys from x and returns the result.",
   :name "-"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1089",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/->",
   :namespace "clojure.core",
   :line 1089,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([x] [x form] [x form & more]),
   :doc
   "Threads the expr through the forms. Inserts x as the\nsecond item in the first form, making a list of it if it is not a\nlist already. If there are more forms, inserts the first form as the\nsecond item in second form, etc.",
   :name "->"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1100",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/->>",
   :namespace "clojure.core",
   :line 1100,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([x form] [x form & more]),
   :doc
   "Threads the expr through the forms. Inserts x as the\nlast item in the first form, making a list of it if it is not a\nlist already. If there are more forms, inserts the first form as the\nlast item in second form, etc.",
   :name "->>"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1072",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/..",
   :namespace "clojure.core",
   :line 1072,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([x form] [x form & more]),
   :doc
   "form => fieldName-symbol or (instanceMethodName-symbol args*)\n\nExpands into a member access (.) of the first member on the first\nargument, followed by the next member on the result, etc. For\ninstance:\n\n(.. System (getProperties) (get \"os.name\"))\n\nexpands to:\n\n(. (. System (getProperties)) (get \"os.name\"))\n\nbut is easier to write, read, and understand.",
   :name ".."}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L697",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core//",
   :namespace "clojure.core",
   :line 697,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x] [x y] [x y & more]),
   :doc
   "If no denominators are supplied, returns 1/numerator,\nelse returns numerator divided by all of the denominators.",
   :name "/"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L627",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/<",
   :namespace "clojure.core",
   :line 627,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x] [x y] [x y & more]),
   :doc
   "Returns non-nil if nums are in monotonically increasing order,\notherwise false.",
   :name "<"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L717",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/<=",
   :namespace "clojure.core",
   :line 717,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x] [x y] [x y & more]),
   :doc
   "Returns non-nil if nums are in monotonically non-decreasing order,\notherwise false.",
   :name "<="}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L539",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/=",
   :namespace "clojure.core",
   :line 539,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x] [x y] [x y & more]),
   :doc
   "Equality. Returns true if x equals y, false if not. Same as\nJava x.equals(y) except it also works for nil, and compares\nnumbers and collections in a type-independent manner.  Clojure's immutable data\nstructures define equals() (and thus =) as a value, not an identity,\ncomparison.",
   :name "="}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L759",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/==",
   :namespace "clojure.core",
   :line 759,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x] [x y] [x y & more]),
   :doc
   "Returns non-nil if nums all have the same value, otherwise false",
   :name "=="}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L731",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/>",
   :namespace "clojure.core",
   :line 731,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x] [x y] [x y & more]),
   :doc
   "Returns non-nil if nums are in monotonically decreasing order,\notherwise false.",
   :name ">"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L745",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/>=",
   :namespace "clojure.core",
   :line 745,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x] [x y] [x y & more]),
   :doc
   "Returns non-nil if nums are in monotonically non-increasing order,\notherwise false.",
   :name ">="}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2612",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/accessor",
   :namespace "clojure.core",
   :line 2612,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([s key]),
   :doc
   "Returns a fn that, given an instance of a structmap with the basis,\nreturns the value at the key.  The key must be in the basis. The\nreturned function should be (slightly) more efficient than using\nget, but such use of accessors should be limited to known\nperformance-critical areas.",
   :name "accessor"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2473",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/aclone",
   :namespace "clojure.core",
   :line 2473,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([array]),
   :doc
   "Returns a clone of the Java array. Works on arrays of known\ntypes.",
   :name "aclone"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3472",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/add-classpath",
   :namespace "clojure.core",
   :line 3472,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([url]),
   :doc
   "DEPRECATED \n\nAdds the url (String or URL object) to the classpath per\nURLClassLoader.addURL",
   :name "add-classpath"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1371",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/add-watch",
   :namespace "clojure.core",
   :line 1371,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([reference key fn]),
   :doc
   "Alpha - subject to change.\nAdds a watch function to an agent/atom/var/ref reference. The watch\nfn must be a fn of 4 args: a key, the reference, its old-state, its\nnew-state. Whenever the reference's state might have been changed,\nany registered watches will have their functions called. The watch fn\nwill be called synchronously, on the agent's thread if an agent,\nbefore any pending sends if agent or ref. Note that an atom's or\nref's state may have changed again prior to the fn call, so use\nold/new-state rather than derefing the reference. Note also that watch\nfns may be called from multiple threads simultaneously. Var watchers\nare triggered only by root binding changes, not thread-local\nset!s. Keys must be unique per reference, and can be used to remove\nthe watch with remove-watch, but are otherwise considered opaque by\nthe watch mechanism.",
   :name "add-watch"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1327",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/agent",
   :namespace "clojure.core",
   :line 1327,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([state] [state & options]),
   :doc
   "Creates and returns an agent with an initial value of state and\nzero or more options (in any order):\n\n:meta metadata-map\n\n:validator validate-fn\n\nIf metadata-map is supplied, it will be come the metadata on the\nagent. validate-fn must be nil or a side-effect-free fn of one\nargument, which will be passed the intended new state on any state\nchange. If the new state is unacceptable, the validate-fn should\nreturn false or throw an exception.",
   :name "agent"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1395",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/agent-errors",
   :namespace "clojure.core",
   :line 1395,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([a]),
   :doc
   "Returns a sequence of the exceptions thrown during asynchronous\nactions of the agent.",
   :name "agent-errors"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2479",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/aget",
   :namespace "clojure.core",
   :line 2479,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([array idx] [array idx & idxs]),
   :doc
   "Returns the value at the index/indices. Works on Java arrays of all\ntypes.",
   :name "aget"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2467",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/alength",
   :namespace "clojure.core",
   :line 2467,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([array]),
   :doc
   "Returns the length of the Java array. Works on arrays of all\ntypes.",
   :name "alength"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2753",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/alias",
   :namespace "clojure.core",
   :line 2753,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([alias namespace-sym]),
   :doc
   "Add an alias in the current namespace to another\nnamespace. Arguments are two symbols: the alias to be used, and\nthe symbolic name of the target namespace. Use :as in the ns macro in preference\nto calling this directly.",
   :name "alias"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2662",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/all-ns",
   :namespace "clojure.core",
   :line 2662,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([]),
   :doc "Returns a sequence of all namespaces.",
   :name "all-ns"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1534",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/alter",
   :namespace "clojure.core",
   :line 1534,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([ref fun & args]),
   :doc
   "Must be called in a transaction. Sets the in-transaction-value of\nref to:\n\n(apply fun in-transaction-value-of-ref args)\n\nand returns the in-transaction-value of ref.",
   :name "alter"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1503",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/alter-meta!",
   :namespace "clojure.core",
   :line 1503,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([iref f & args]),
   :doc
   "Atomically sets the metadata for a namespace/var/ref/agent/atom to be:\n\n(apply f its-current-meta args)\n\nf must be free of side-effects",
   :name "alter-meta!"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3664",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/alter-var-root",
   :namespace "clojure.core",
   :line 3664,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([v f & args]),
   :doc
   "Atomically alters the root binding of var v by applying f to its\ncurrent value plus any args",
   :name "alter-var-root"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3508",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/amap",
   :namespace "clojure.core",
   :line 3508,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([a idx ret expr]),
   :doc
   "Maps an expression across an array a, using an index named idx, and\nreturn value named ret, initialized to a clone of a, then setting \neach element of ret to the evaluation of expr, returning the new \narray ret.",
   :name "amap"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3729",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/ancestors",
   :namespace "clojure.core",
   :line 3729,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([tag] [h tag]),
   :doc
   "Returns the immediate and indirect parents of tag, either via a Java type\ninheritance relationship or a relationship established via derive. h\nmust be a hierarchy obtained from make-hierarchy, if not supplied\ndefaults to the global hierarchy",
   :name "ancestors"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L577",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/and",
   :namespace "clojure.core",
   :line 577,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([] [x] [x & next]),
   :doc
   "Evaluates exprs one at a time, from left to right. If a form\nreturns logical false (nil or false), and returns that value and\ndoesn't evaluate any of the other expressions, otherwise it returns\nthe value of the last expr. (and) returns true.",
   :name "and"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L432",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/apply",
   :namespace "clojure.core",
   :line 432,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([f args* argseq]),
   :doc
   "Applies fn f to the argument list formed by prepending args to argseq.",
   :name "apply"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3523",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/areduce",
   :namespace "clojure.core",
   :line 3523,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([a idx ret init expr]),
   :doc
   "Reduces an expression across an array a, using an index named idx,\nand return value named ret, initialized to init, setting ret to the \nevaluation of expr at each step, returning ret.",
   :name "areduce"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2831",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/array-map",
   :namespace "clojure.core",
   :line 2831,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([] [& keyvals]),
   :doc "Constructs an array-map.",
   :name "array-map"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2489",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/aset",
   :namespace "clojure.core",
   :line 2489,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([array idx val] [array idx idx2 & idxv]),
   :doc
   "Sets the value at the index/indices. Works on Java arrays of\nreference types. Returns val.",
   :name "aset"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2519",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/aset-boolean",
   :namespace "clojure.core",
   :line 2519,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([array idx val] [array idx idx2 & idxv]),
   :doc
   "Sets the value at the index/indices. Works on arrays of boolean. Returns val.",
   :name "aset-boolean"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2535",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/aset-byte",
   :namespace "clojure.core",
   :line 2535,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([array idx val] [array idx idx2 & idxv]),
   :doc
   "Sets the value at the index/indices. Works on arrays of byte. Returns val.",
   :name "aset-byte"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2539",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/aset-char",
   :namespace "clojure.core",
   :line 2539,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([array idx val] [array idx idx2 & idxv]),
   :doc
   "Sets the value at the index/indices. Works on arrays of char. Returns val.",
   :name "aset-char"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2527",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/aset-double",
   :namespace "clojure.core",
   :line 2527,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([array idx val] [array idx idx2 & idxv]),
   :doc
   "Sets the value at the index/indices. Works on arrays of double. Returns val.",
   :name "aset-double"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2523",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/aset-float",
   :namespace "clojure.core",
   :line 2523,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([array idx val] [array idx idx2 & idxv]),
   :doc
   "Sets the value at the index/indices. Works on arrays of float. Returns val.",
   :name "aset-float"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2511",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/aset-int",
   :namespace "clojure.core",
   :line 2511,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([array idx val] [array idx idx2 & idxv]),
   :doc
   "Sets the value at the index/indices. Works on arrays of int. Returns val.",
   :name "aset-int"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2515",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/aset-long",
   :namespace "clojure.core",
   :line 2515,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([array idx val] [array idx idx2 & idxv]),
   :doc
   "Sets the value at the index/indices. Works on arrays of long. Returns val.",
   :name "aset-long"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2531",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/aset-short",
   :namespace "clojure.core",
   :line 2531,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([array idx val] [array idx idx2 & idxv]),
   :doc
   "Sets the value at the index/indices. Works on arrays of short. Returns val.",
   :name "aset-short"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3149",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/assert",
   :namespace "clojure.core",
   :line 3149,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([x]),
   :doc
   "Evaluates expr and throws an exception if it does not evaluate to\nlogical true.",
   :name "assert"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L138",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/assoc",
   :namespace "clojure.core",
   :line 138,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([map key val] [map key val & kvs]),
   :doc
   "assoc[iate]. When applied to a map, returns a new map of the\nsame (hashed/sorted) type, that contains the mapping of key(s) to\nval(s). When applied to a vector, returns a new vector that\ncontains val at index. Note - index must be <= (count vector).",
   :name "assoc"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4624",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/assoc!",
   :namespace "clojure.core",
   :line 4624,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll key val] [coll key val & kvs]),
   :doc
   "Alpha - subject to change.\nWhen applied to a transient map, adds mapping of key(s) to\nval(s). When applied to a transient vector, sets the val at index.\nNote - index must be <= (count vector). Returns coll.",
   :name "assoc!"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4191",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/assoc-in",
   :namespace "clojure.core",
   :line 4191,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([m [k & ks] v]),
   :doc
   "Associates a value in a nested associative structure, where ks is a\nsequence of keys and v is the new value and returns a new nested structure.\nIf any levels do not exist, hash-maps will be created.",
   :name "assoc-in"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4239",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/associative?",
   :namespace "clojure.core",
   :line 4239,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc "Returns true if coll implements Associative",
   :name "associative?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1453",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/atom",
   :namespace "clojure.core",
   :line 1453,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x] [x & options]),
   :doc
   "Creates and returns an Atom with an initial value of x and zero or\nmore options (in any order):\n\n:meta metadata-map\n\n:validator validate-fn\n\nIf metadata-map is supplied, it will be come the metadata on the\natom. validate-fn must be nil or a side-effect-free fn of one\nargument, which will be passed the intended new state on any state\nchange. If the new state is unacceptable, the validate-fn should\nreturn false or throw an exception.",
   :name "atom"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2103",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/await",
   :namespace "clojure.core",
   :line 2103,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([& agents]),
   :doc
   "Blocks the current thread (indefinitely!) until all actions\ndispatched thus far, from this thread or agent, to the agent(s) have\noccurred.",
   :name "await"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2122",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/await-for",
   :namespace "clojure.core",
   :line 2122,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([timeout-ms & agents]),
   :doc
   "Blocks the current thread until all actions dispatched thus\nfar (from this thread or agent) to the agents have occurred, or the\ntimeout (in milliseconds) has elapsed. Returns nil if returning due\nto timeout, non-nil otherwise.",
   :name "await-for"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3680",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/bases",
   :namespace "clojure.core",
   :line 3680,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([c]),
   :doc
   "Returns the immediate superclass and direct interfaces of c, if any",
   :name "bases"}
  {:source-url
   "http://github.com/clojure/clojure/blob/040f083efc16dd830a4508a35a04465e3e5677d3/src/clj/clojure/core_proxy.clj#L360",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/bean",
   :namespace "clojure.core",
   :line 360,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/core_proxy.clj",
   :var-type "function",
   :arglists ([x]),
   :doc
   "Takes a Java object and returns a read-only implementation of the\nmap abstraction based upon its JavaBean properties.",
   :name "bean"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2301",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/bigdec",
   :namespace "clojure.core",
   :line 2301,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Coerce to BigDecimal",
   :name "bigdec"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2292",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/bigint",
   :namespace "clojure.core",
   :line 2292,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Coerce to BigInteger",
   :name "bigint"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1251",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/binding",
   :namespace "clojure.core",
   :line 1251,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([bindings & body]),
   :doc
   "binding => var-symbol init-expr\n\nCreates new bindings for the (already-existing) vars, with the\nsupplied initial values, executes the exprs in an implicit do, then\nre-establishes the bindings that existed before.  The new bindings\nare made in parallel (unlike let); all init-exprs are evaluated\nbefore the vars are bound to their new values.",
   :name "binding"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L874",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/bit-and",
   :namespace "clojure.core",
   :line 874,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x y]),
   :doc "Bitwise and",
   :name "bit-and"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L889",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/bit-and-not",
   :namespace "clojure.core",
   :line 889,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x y]),
   :doc "Bitwise and with complement",
   :name "bit-and-not"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L894",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/bit-clear",
   :namespace "clojure.core",
   :line 894,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x n]),
   :doc "Clear bit at index n",
   :name "bit-clear"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L902",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/bit-flip",
   :namespace "clojure.core",
   :line 902,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x n]),
   :doc "Flip bit at index n",
   :name "bit-flip"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L868",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/bit-not",
   :namespace "clojure.core",
   :line 868,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Bitwise complement",
   :name "bit-not"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L879",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/bit-or",
   :namespace "clojure.core",
   :line 879,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x y]),
   :doc "Bitwise or",
   :name "bit-or"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L898",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/bit-set",
   :namespace "clojure.core",
   :line 898,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x n]),
   :doc "Set bit at index n",
   :name "bit-set"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L911",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/bit-shift-left",
   :namespace "clojure.core",
   :line 911,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x n]),
   :doc "Bitwise shift left",
   :name "bit-shift-left"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L915",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/bit-shift-right",
   :namespace "clojure.core",
   :line 915,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x n]),
   :doc "Bitwise shift right",
   :name "bit-shift-right"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L906",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/bit-test",
   :namespace "clojure.core",
   :line 906,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x n]),
   :doc "Test bit at index n",
   :name "bit-test"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L884",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/bit-xor",
   :namespace "clojure.core",
   :line 884,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x y]),
   :doc "Bitwise exclusive or",
   :name "bit-xor"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2246",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/boolean",
   :namespace "clojure.core",
   :line 2246,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Coerce to boolean",
   :name "boolean"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3541",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/boolean-array",
   :namespace "clojure.core",
   :line 3541,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([size-or-seq] [size init-val-or-seq]),
   :doc "Creates an array of booleans",
   :name "boolean-array"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3590",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/booleans",
   :namespace "clojure.core",
   :line 3590,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([xs]),
   :doc "Casts to boolean[]",
   :name "booleans"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1304",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/bound-fn",
   :namespace "clojure.core",
   :line 1304,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([& fntail]),
   :doc
   "Returns a function defined by the given fntail, which will install the\nsame bindings in effect as in the thread at the time bound-fn was called.\nThis may be used to define a helper function which runs on a different\nthread, but needs the same bindings in place.",
   :name "bound-fn"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1294",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/bound-fn*",
   :namespace "clojure.core",
   :line 1294,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([f]),
   :doc
   "Returns a function, which will install the same bindings in effect as in\nthe thread at the time bound-fn* was called and then call f with any given\narguments. This may be used to define a helper function which runs on a\ndifferent thread, but needs the same bindings in place.",
   :name "bound-fn*"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L197",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/butlast",
   :namespace "clojure.core",
   :line 197,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc
   "Return a seq of all but the last item in coll, in linear time",
   :name "butlast"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2234",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/byte",
   :namespace "clojure.core",
   :line 2234,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Coerce to byte",
   :name "byte"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3548",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/byte-array",
   :namespace "clojure.core",
   :line 3548,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([size-or-seq] [size init-val-or-seq]),
   :doc "Creates an array of bytes",
   :name "byte-array"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3594",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/bytes",
   :namespace "clojure.core",
   :line 3594,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([xs]),
   :doc "Casts to bytes[]",
   :name "bytes"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L250",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/cast",
   :namespace "clojure.core",
   :line 250,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([c x]),
   :doc "Throws a ClassCastException if x is not a c, else returns x.",
   :name "cast"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2240",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/char",
   :namespace "clojure.core",
   :line 2240,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Coerce to char",
   :name "char"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3555",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/char-array",
   :namespace "clojure.core",
   :line 3555,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([size-or-seq] [size init-val-or-seq]),
   :doc "Creates an array of chars",
   :name "char-array"}
  {:source-url
   "http://github.com/clojure/clojure/blob/1a0e23d0e78ef3d3a3a6267a68adcfc414d3fb56/src/clj/clojure/core_print.clj#L165",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/char-escape-string",
   :namespace "clojure.core",
   :line 165,
   :file "clojure/core_print.clj",
   :var-type "var",
   :doc "Returns escape string for char or nil if none",
   :name "char-escape-string"}
  {:source-url
   "http://github.com/clojure/clojure/blob/1a0e23d0e78ef3d3a3a6267a68adcfc414d3fb56/src/clj/clojure/core_print.clj#L223",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/char-name-string",
   :namespace "clojure.core",
   :line 223,
   :file "clojure/core_print.clj",
   :var-type "var",
   :doc "Returns name string for char or nil if none",
   :name "char-name-string"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L118",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/char?",
   :namespace "clojure.core",
   :line 118,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Return true if x is a Character",
   :name "char?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3598",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/chars",
   :namespace "clojure.core",
   :line 3598,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([xs]),
   :doc "Casts to chars[]",
   :name "chars"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2195",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/class",
   :namespace "clojure.core",
   :line 2195,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Returns the Class of x",
   :name "class"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3660",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/class?",
   :namespace "clojure.core",
   :line 3660,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Returns true if x is an instance of Class",
   :name "class?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1400",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/clear-agent-errors",
   :namespace "clojure.core",
   :line 1400,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([a]),
   :doc
   "Clears any exceptions thrown during asynchronous actions of the\nagent, allowing subsequent actions to occur.",
   :name "clear-agent-errors"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4563",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/clojure-version",
   :namespace "clojure.core",
   :line 4563,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([]),
   :doc "Returns clojure version as a printable string.",
   :name "clojure-version"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4217",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/coll?",
   :namespace "clojure.core",
   :line 4217,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Returns true if x implements IPersistentCollection",
   :name "coll?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3099",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/comment",
   :namespace "clojure.core",
   :line 3099,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([& body]),
   :doc "Ignores body, yields nil",
   :name "comment"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1515",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/commute",
   :namespace "clojure.core",
   :line 1515,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([ref fun & args]),
   :doc
   "Must be called in a transaction. Sets the in-transaction-value of\nref to:\n\n(apply fun in-transaction-value-of-ref args)\n\nand returns the in-transaction-value of ref.\n\nAt the commit point of the transaction, sets the value of ref to be:\n\n(apply fun most-recently-committed-value-of-ref args)\n\nThus fun should be commutative, or, failing that, you must accept\nlast-one-in-wins behavior.  commute allows for more concurrency than\nref-set.",
   :name "commute"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1605",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/comp",
   :namespace "clojure.core",
   :line 1605,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([f] [f g] [f g h] [f1 f2 f3 & fs]),
   :doc
   "Takes a set of functions and returns a fn that is the composition\nof those fns.  The returned fn takes a variable number of args,\napplies the rightmost of fns to the args, the next\nfn (right-to-left) to the result, etc.",
   :name "comp"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1962",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/comparator",
   :namespace "clojure.core",
   :line 1962,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([pred]),
   :doc
   "Returns an implementation of java.util.Comparator based upon pred.",
   :name "comparator"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L567",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/compare",
   :namespace "clojure.core",
   :line 567,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x y]),
   :doc
   "Comparator. Returns a negative number, zero, or a positive number\nwhen x is logically 'less than', 'equal to', or 'greater than'\ny. Same as Java x.compareTo(y) except it also works for nil, and\ncompares numbers and collections in a type-independent manner. x\nmust implement Comparable",
   :name "compare"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1479",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/compare-and-set!",
   :namespace "clojure.core",
   :line 1479,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([atom oldval newval]),
   :doc
   "Atomically sets the value of atom to newval if and only if the\ncurrent value of the atom is identical to oldval. Returns true if\nset happened, else false",
   :name "compare-and-set!"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4173",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/compile",
   :namespace "clojure.core",
   :line 4173,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([lib]),
   :doc
   "Compiles the namespace named by the symbol lib into a set of\nclassfiles. The source for the lib must be in a proper\nclasspath-relative directory. The output files will go into the\ndirectory specified by *compile-path*, and that directory too must\nbe in the classpath.",
   :name "compile"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L930",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/complement",
   :namespace "clojure.core",
   :line 930,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([f]),
   :doc
   "Takes a fn f and returns a fn that takes the same arguments as f,\nhas the same effects, if any, and returns the opposite truth value.",
   :name "complement"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L488",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/concat",
   :namespace "clojure.core",
   :line 488,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([] [x] [x y] [x y & zs]),
   :doc
   "Returns a lazy seq representing the concatenation of the elements in the supplied colls.",
   :name "concat"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L400",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/cond",
   :namespace "clojure.core",
   :line 400,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([& clauses]),
   :doc
   "Takes a set of test/expr pairs. It evaluates each test one at a\ntime.  If a test returns logical true, cond evaluates and returns\nthe value of the corresponding expr and doesn't evaluate any of the\nother tests or exprs. (cond) returns nil.",
   :name "cond"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4332",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/condp",
   :namespace "clojure.core",
   :line 4332,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([pred expr & clauses]),
   :doc
   "Takes a binary predicate, an expression, and a set of clauses.\nEach clause can take the form of either:\n\ntest-expr result-expr\n\ntest-expr :>> result-fn\n\nNote :>> is an ordinary keyword.\n\nFor each clause, (pred test-expr expr) is evaluated. If it returns\nlogical true, the clause is a match. If a binary clause matches, the\nresult-expr is returned, if a ternary clause matches, its result-fn,\nwhich must be a unary function, is called with the result of the\npredicate as its argument, the result of that call being the return\nvalue of condp. A single default expression can follow the clauses,\nand its value will be returned if no clause matches. If no default\nexpression is provided and no clause matches, an\nIllegalArgumentException is thrown.",
   :name "condp"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L61",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/conj",
   :namespace "clojure.core",
   :line 61,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll x] [coll x & xs]),
   :doc
   "conj[oin]. Returns a new collection with the xs\n'added'. (conj nil item) returns (item).  The 'addition' may\nhappen at different 'places' depending on the concrete type.",
   :name "conj"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4617",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/conj!",
   :namespace "clojure.core",
   :line 4617,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll x]),
   :doc
   "Alpha - subject to change.\nAdds x to the transient collection, and return coll. The 'addition'\nmay happen at different 'places' depending on the concrete type.",
   :name "conj!"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L21",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/cons",
   :namespace "clojure.core",
   :line 21,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x seq]),
   :doc
   "Returns a new seq where x is the first element and seq is\nthe rest.",
   :name "cons"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L940",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/constantly",
   :namespace "clojure.core",
   :line 940,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc
   "Returns a function that takes any number of arguments and returns x.",
   :name "constantly"}
  {:source-url
   "http://github.com/clojure/clojure/blob/040f083efc16dd830a4508a35a04465e3e5677d3/src/clj/clojure/core_proxy.clj#L263",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/construct-proxy",
   :namespace "clojure.core",
   :line 263,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/core_proxy.clj",
   :var-type "function",
   :arglists ([c & ctor-args]),
   :doc
   "Takes a proxy class and any arguments for its superclass ctor and\ncreates and returns an instance of the proxy.",
   :name "construct-proxy"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L969",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/contains?",
   :namespace "clojure.core",
   :line 969,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll key]),
   :doc
   "Returns true if key is present in the given collection, otherwise\nreturns false.  Note that for numerically indexed collections like\nvectors and Java arrays, this tests if the numeric key is within the\nrange of indexes. 'contains?' operates constant or logarithmic time;\nit will not perform a linear search for a value.  See also 'some'.",
   :name "contains?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L606",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/count",
   :namespace "clojure.core",
   :line 606,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc
   "Returns the number of items in the collection. (count nil) returns\n0.  Also works on strings, arrays, and Java Collections and Maps",
   :name "count"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4251",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/counted?",
   :namespace "clojure.core",
   :line 4251,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc "Returns true if coll implements count in constant time",
   :name "counted?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2651",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/create-ns",
   :namespace "clojure.core",
   :line 2651,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([sym]),
   :doc
   "Create a new namespace named by the symbol if one doesn't already\nexist, returns it or the already-existing namespace of the same\nname.",
   :name "create-ns"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2587",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/create-struct",
   :namespace "clojure.core",
   :line 2587,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([& keys]),
   :doc "Returns a structure basis object.",
   :name "create-struct"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1868",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/cycle",
   :namespace "clojure.core",
   :line 1868,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc
   "Returns a lazy (infinite!) sequence of repetitions of the items in coll.",
   :name "cycle"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L786",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/dec",
   :namespace "clojure.core",
   :line 786,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Returns a number one less than num.",
   :name "dec"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2278",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/decimal?",
   :namespace "clojure.core",
   :line 2278,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([n]),
   :doc "Returns true if n is a BigDecimal",
   :name "decimal?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4275",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/declare",
   :namespace "clojure.core",
   :line 4275,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([& names]),
   :doc
   "defs the supplied var names with no bindings, useful for making forward declarations.",
   :name "declare"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3491",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/definline",
   :namespace "clojure.core",
   :line 3491,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([name & decl]),
   :doc
   "Experimental - like defmacro, except defines a named function whose\nbody is the expansion, calls to which may be expanded inline as if\nit were a macro. Cannot be used with variadic (&) args.",
   :name "definline"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L311",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/defmacro",
   :namespace "clojure.core",
   :line 311,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists
   ([name doc-string? attr-map? [params*] body]
    [name doc-string? attr-map? ([params*] body) + attr-map?]),
   :doc
   "Like defn, but the resulting function name is declared as a\nmacro and will be used as a macro by the compiler when it is\ncalled.",
   :name "defmacro"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1152",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/defmethod",
   :namespace "clojure.core",
   :line 1152,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([multifn dispatch-val & fn-tail]),
   :doc
   "Creates and installs a new method of multimethod associated with dispatch-value. ",
   :name "defmethod"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1113",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/defmulti",
   :namespace "clojure.core",
   :line 1113,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([name docstring? attr-map? dispatch-fn & options]),
   :doc
   "Creates a new multimethod with the associated dispatch function.\nThe docstring and attribute-map are optional.\n\nOptions are key-value pairs and may be one of:\n  :default    the default dispatch value, defaults to :default\n  :hierarchy  the isa? hierarchy to use for dispatching\n              defaults to the global hierarchy",
   :name "defmulti"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L206",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/defn",
   :namespace "clojure.core",
   :line 206,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists
   ([name doc-string? attr-map? [params*] body]
    [name doc-string? attr-map? ([params*] body) + attr-map?]),
   :doc
   "Same as (def name (fn [params* ] exprs*)) or (def\nname (fn ([params* ] exprs*)+)) with any doc-string or attrs added\nto the var metadata",
   :name "defn"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3236",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/defn-",
   :namespace "clojure.core",
   :line 3236,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([name & decls]),
   :doc "same as defn, yielding non-public def",
   :name "defn-"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3932",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/defonce",
   :namespace "clojure.core",
   :line 3932,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([name expr]),
   :doc
   "defs name to have the root value of the expr iff the named var has no root value,\nelse expr is unevaluated",
   :name "defonce"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2592",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/defstruct",
   :namespace "clojure.core",
   :line 2592,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([name & keys]),
   :doc "Same as (def name (create-struct keys...))",
   :name "defstruct"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L516",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/delay",
   :namespace "clojure.core",
   :line 516,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([& body]),
   :doc
   "Takes a body of expressions and yields a Delay object that will\ninvoke the body only the first time it is forced (with force), and\nwill cache the result and return it on all subsequent force\ncalls.",
   :name "delay"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L524",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/delay?",
   :namespace "clojure.core",
   :line 524,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "returns true if x is a Delay created with delay",
   :name "delay?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4596",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/deliver",
   :namespace "clojure.core",
   :line 4596,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([promise val]),
   :doc
   "Alpha - subject to change.\nDelivers the supplied value to the promise, releasing any pending\nderefs. A subsequent call to deliver on a promise will throw an exception.",
   :name "deliver"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1444",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/deref",
   :namespace "clojure.core",
   :line 1444,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([ref]),
   :doc
   "Also reader macro: @ref/@agent/@var/@atom/@delay/@future. Within a transaction,\nreturns the in-transaction-value of ref, else returns the\nmost-recently-committed value of ref. When applied to a var, agent\nor atom, returns its current state. When applied to a delay, forces\nit if not already forced. When applied to a future, will block if\ncomputation not complete",
   :name "deref"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3755",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/derive",
   :namespace "clojure.core",
   :line 3755,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([tag parent] [h tag parent]),
   :doc
   "Establishes a parent/child relationship between parent and\ntag. Parent must be a namespace-qualified symbol or keyword and\nchild can be either a namespace-qualified symbol or keyword or a\nclass. h must be a hierarchy obtained from make-hierarchy, if not\nsupplied defaults to, and modifies, the global hierarchy.",
   :name "derive"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3744",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/descendants",
   :namespace "clojure.core",
   :line 3744,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([tag] [h tag]),
   :doc
   "Returns the immediate and indirect children of tag, through a\nrelationship established via derive. h must be a hierarchy obtained\nfrom make-hierarchy, if not supplied defaults to the global\nhierarchy. Note: does not work on Java type inheritance\nrelationships.",
   :name "descendants"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L996",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/disj",
   :namespace "clojure.core",
   :line 996,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([set] [set key] [set key & ks]),
   :doc
   "disj[oin]. Returns a new set of the same (hashed/sorted) type, that\ndoes not contain key(s).",
   :name "disj"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4653",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/disj!",
   :namespace "clojure.core",
   :line 4653,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([set] [set key] [set key & ks]),
   :doc
   "Alpha - subject to change.\ndisj[oin]. Returns a transient set of the same (hashed/sorted) type, that\ndoes not contain key(s).",
   :name "disj!"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L984",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/dissoc",
   :namespace "clojure.core",
   :line 984,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([map] [map key] [map key & ks]),
   :doc
   "dissoc[iate]. Returns a new map of the same (hashed/sorted) type,\nthat does not contain a mapping for key(s).",
   :name "dissoc"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4636",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/dissoc!",
   :namespace "clojure.core",
   :line 4636,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([map key] [map key & ks]),
   :doc
   "Alpha - subject to change.\nReturns a transient map that doesn't contain a mapping for key(s).",
   :name "dissoc!"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3378",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/distinct",
   :namespace "clojure.core",
   :line 3378,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc
   "Returns a lazy sequence of the elements of coll with duplicates removed",
   :name "distinct"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3812",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/distinct?",
   :namespace "clojure.core",
   :line 3812,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x] [x y] [x y & more]),
   :doc "Returns true if no two of the arguments are =",
   :name "distinct?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2089",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/doall",
   :namespace "clojure.core",
   :line 2089,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll] [n coll]),
   :doc
   "When lazy sequences are produced via functions that have side\neffects, any effects other than those needed to produce the first\nelement in the seq do not occur until the seq is consumed. doall can\nbe used to force any effects. Walks through the successive nexts of\nthe seq, retains the head and returns it, thus causing the entire\nseq to reside in memory at one time.",
   :name "doall"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3288",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/doc",
   :namespace "clojure.core",
   :line 3288,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([name]),
   :doc
   "Prints documentation for a var or special form given its name",
   :name "doc"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2076",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/dorun",
   :namespace "clojure.core",
   :line 2076,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll] [n coll]),
   :doc
   "When lazy sequences are produced via functions that have side\neffects, any effects other than those needed to produce the first\nelement in the seq do not occur until the seq is consumed. dorun can\nbe used to force any effects. Walks through the successive nexts of\nthe seq, does not retain the head and returns nil.",
   :name "dorun"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2019",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/doseq",
   :namespace "clojure.core",
   :line 2019,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([seq-exprs & body]),
   :doc
   "Repeatedly executes body (presumably for side-effects) with\nbindings and filtering as provided by \"for\".  Does not retain\nthe head of the sequence. Returns nil.",
   :name "doseq"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3406",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/dosync",
   :namespace "clojure.core",
   :line 3406,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([& exprs]),
   :doc
   "Runs the exprs (in an implicit do) in a transaction that encompasses\nexprs and any nested calls.  Starts a transaction if none is already\nrunning on this thread. Any uncaught exception will abort the\ntransaction and flow out of dosync. The exprs may be run more than\nonce, but any effects on Refs will be atomic.",
   :name "dosync"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2137",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/dotimes",
   :namespace "clojure.core",
   :line 2137,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([bindings & body]),
   :doc
   "bindings => name n\n\nRepeatedly executes body (presumably for side-effects) with name\nbound to integers from 0 through n-1.",
   :name "dotimes"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2429",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/doto",
   :namespace "clojure.core",
   :line 2429,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([x & forms]),
   :doc
   "Evaluates x then calls all of the methods and functions with the\nvalue of x supplied at the from of the given arguments.  The forms\nare evaluated in order.  Returns x.\n\n(doto (new java.util.HashMap) (.put \"a\" 1) (.put \"b\" 2))",
   :name "doto"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2222",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/double",
   :namespace "clojure.core",
   :line 2222,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Coerce to double",
   :name "double"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3569",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/double-array",
   :namespace "clojure.core",
   :line 3569,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([size-or-seq] [size init-val-or-seq]),
   :doc "Creates an array of doubles",
   :name "double-array"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3614",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/doubles",
   :namespace "clojure.core",
   :line 3614,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([xs]),
   :doc "Casts to double[]",
   :name "doubles"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1833",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/drop",
   :namespace "clojure.core",
   :line 1833,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([n coll]),
   :doc
   "Returns a lazy sequence of all but the first n items in coll.",
   :name "drop"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1843",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/drop-last",
   :namespace "clojure.core",
   :line 1843,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([s] [n s]),
   :doc
   "Return a lazy sequence of all but the last n (default 1) items in coll",
   :name "drop-last"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1857",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/drop-while",
   :namespace "clojure.core",
   :line 1857,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([pred coll]),
   :doc
   "Returns a lazy sequence of the items in coll starting from the first\nitem for which (pred item) returns nil.",
   :name "drop-while"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3502",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/empty",
   :namespace "clojure.core",
   :line 3502,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc
   "Returns an empty collection of the same category as coll, or nil",
   :name "empty"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4212",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/empty?",
   :namespace "clojure.core",
   :line 4212,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc
   "Returns true if coll has no items - same as (not (seq coll)).\nPlease use the idiom (seq x) rather than (not (empty? x))",
   :name "empty?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1569",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/ensure",
   :namespace "clojure.core",
   :line 1569,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([ref]),
   :doc
   "Must be called in a transaction. Protects the ref from modification\nby other transactions.  Returns the in-transaction-value of\nref. Allows for more concurrency than (ref-set ref @ref)",
   :name "ensure"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3851",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/enumeration-seq",
   :namespace "clojure.core",
   :line 3851,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([e]),
   :doc "Returns a seq on a java.util.Enumeration",
   :name "enumeration-seq"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2015",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/eval",
   :namespace "clojure.core",
   :line 2015,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([form]),
   :doc
   "Evaluates the form data structure (not text!) and returns the result.",
   :name "eval"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L919",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/even?",
   :namespace "clojure.core",
   :line 919,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([n]),
   :doc
   "Returns true if n is even, throws an exception if n is not an integer",
   :name "even?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1698",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/every?",
   :namespace "clojure.core",
   :line 1698,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([pred coll]),
   :doc
   "Returns true if (pred x) is logical true for every x in coll, else\nfalse.",
   :name "every?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L341",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/false?",
   :namespace "clojure.core",
   :line 341,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Returns true if x is the value false, false otherwise.",
   :name "false?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L78",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/ffirst",
   :namespace "clojure.core",
   :line 78,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Same as (first (first x))",
   :name "ffirst"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3317",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/file-seq",
   :namespace "clojure.core",
   :line 3317,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([dir]),
   :doc "A tree seq on java.io.Files",
   :name "file-seq"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1789",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/filter",
   :namespace "clojure.core",
   :line 1789,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([pred coll]),
   :doc
   "Returns a lazy sequence of the items in coll for which\n(pred item) returns true. pred must be free of side-effects.",
   :name "filter"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1008",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/find",
   :namespace "clojure.core",
   :line 1008,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([map key]),
   :doc "Returns the map entry for key, or nil if key not present.",
   :name "find"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3249",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/find-doc",
   :namespace "clojure.core",
   :line 3249,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([re-string-or-pattern]),
   :doc
   "Prints documentation for any var whose documentation or name\ncontains a match for re-string-or-pattern",
   :name "find-doc"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2647",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/find-ns",
   :namespace "clojure.core",
   :line 2647,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([sym]),
   :doc
   "Returns the namespace named by the symbol or nil if it doesn't exist.",
   :name "find-ns"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1312",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/find-var",
   :namespace "clojure.core",
   :line 1312,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([sym]),
   :doc
   "Returns the global var named by the namespace-qualified symbol, or\nnil if no var with that name.",
   :name "find-var"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L41",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/first",
   :namespace "clojure.core",
   :line 41,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc
   "Returns the first item in the collection. Calls seq on its\nargument. If coll is nil, returns nil.",
   :name "first"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2216",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/float",
   :namespace "clojure.core",
   :line 2216,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Coerce to float",
   :name "float"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3534",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/float-array",
   :namespace "clojure.core",
   :line 3534,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([size-or-seq] [size init-val-or-seq]),
   :doc "Creates an array of floats",
   :name "float-array"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2282",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/float?",
   :namespace "clojure.core",
   :line 2282,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([n]),
   :doc "Returns true if n is a floating point number",
   :name "float?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3606",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/floats",
   :namespace "clojure.core",
   :line 3606,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([xs]),
   :doc "Casts to float[]",
   :name "floats"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2345",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/flush",
   :namespace "clojure.core",
   :line 2345,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([]),
   :doc
   "Flushes the output stream that is the current value of\n*out*",
   :name "flush"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2913",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/fn",
   :namespace "clojure.core",
   :line 2913,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([& sigs]),
   :doc
   "(fn name? [params* ] exprs*)\n(fn name? ([params* ] exprs*)+)\n\nparams => positional-params* , or positional-params* & next-param\npositional-param => binding-form\nnext-param => binding-form\nname => symbol\n\nDefines a function",
   :name "fn"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4234",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/fn?",
   :namespace "clojure.core",
   :line 4234,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc
   "Returns true if x implements Fn, i.e. is an object created via fn.",
   :name "fn?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L88",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/fnext",
   :namespace "clojure.core",
   :line 88,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Same as (first (next x))",
   :name "fnext"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3013",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/for",
   :namespace "clojure.core",
   :line 3013,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([seq-exprs body-expr]),
   :doc
   "List comprehension. Takes a vector of one or more\n binding-form/collection-expr pairs, each followed by zero or more\n modifiers, and yields a lazy sequence of evaluations of expr.\n Collections are iterated in a nested fashion, rightmost fastest,\n and nested coll-exprs can refer to bindings created in prior\n binding-forms.  Supported modifiers are: :let [binding-form expr ...],\n :while test, :when test.\n\n(take 100 (for [x (range 100000000) y (range 1000000) :while (< y x)] [x y]))",
   :name "for"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L528",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/force",
   :namespace "clojure.core",
   :line 528,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc
   "If x is a Delay, returns the (possibly cached) value of its expression, else returns x",
   :name "force"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3856",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/format",
   :namespace "clojure.core",
   :line 3856,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([fmt & args]),
   :doc
   "Formats a string using java.lang.String.format, see java.util.Formatter for format\nstring syntax",
   :name "format"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4481",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/future",
   :namespace "clojure.core",
   :line 4481,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([& body]),
   :doc
   "Takes a body of expressions and yields a future object that will\ninvoke the body in another thread, and will cache the result and\nreturn it on all subsequent calls to deref/@. If the computation has\nnot yet finished, calls to deref/@ will block.",
   :name "future"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4466",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/future-call",
   :namespace "clojure.core",
   :line 4466,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([f]),
   :doc
   "Takes a function of no args and yields a future object that will\ninvoke the function in another thread, and will cache the result and\nreturn it on all subsequent calls to deref/@. If the computation has\nnot yet finished, calls to deref/@ will block.",
   :name "future-call"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4489",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/future-cancel",
   :namespace "clojure.core",
   :line 4489,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([f]),
   :doc "Cancels the future, if possible.",
   :name "future-cancel"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4493",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/future-cancelled?",
   :namespace "clojure.core",
   :line 4493,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([f]),
   :doc "Returns true if future f is cancelled",
   :name "future-cancelled?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4455",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/future-done?",
   :namespace "clojure.core",
   :line 4455,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([f]),
   :doc "Returns true if future f is done",
   :name "future-done?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4451",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/future?",
   :namespace "clojure.core",
   :line 4451,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Returns true if x is a future",
   :name "future?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/6109d41a975bf24b17681342591116a9897e4a27/src/clj/clojure/genclass.clj#L464",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/gen-class",
   :namespace "clojure.core",
   :line 464,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/genclass.clj",
   :var-type "macro",
   :arglists ([& options]),
   :doc
   "When compiling, generates compiled bytecode for a class with the\ngiven package-qualified :name (which, as all names in these\nparameters, can be a string or symbol), and writes the .class file\nto the *compile-path* directory.  When not compiling, does\nnothing. The gen-class construct contains no implementation, as the\nimplementation will be dynamically sought by the generated class in\nfunctions in an implementing Clojure namespace. Given a generated\nclass org.mydomain.MyClass with a method named mymethod, gen-class\nwill generate an implementation that looks for a function named by \n(str prefix mymethod) (default prefix: \"-\") in a\nClojure namespace specified by :impl-ns\n(defaults to the current namespace). All inherited methods,\ngenerated methods, and init and main functions (see :methods, :init,\nand :main below) will be found similarly prefixed. By default, the\nstatic initializer for the generated class will attempt to load the\nClojure support code for the class as a resource from the classpath,\ne.g. in the example case, ``org/mydomain/MyClass__init.class``. This\nbehavior can be controlled by :load-impl-ns\n\nNote that methods with a maximum of 18 parameters are supported.\n\nIn all subsequent sections taking types, the primitive types can be\nreferred to by their Java names (int, float etc), and classes in the\njava.lang package can be used without a package qualifier. All other\nclasses must be fully qualified.\n\nOptions should be a set of key/value pairs, all except for :name are optional:\n\n:name aname\n\nThe package-qualified name of the class to be generated\n\n:extends aclass\n\nSpecifies the superclass, the non-private methods of which will be\noverridden by the class. If not provided, defaults to Object.\n\n:implements [interface ...]\n\nOne or more interfaces, the methods of which will be implemented by the class.\n\n:init name\n\nIf supplied, names a function that will be called with the arguments\nto the constructor. Must return [ [superclass-constructor-args] state] \nIf not supplied, the constructor args are passed directly to\nthe superclass constructor and the state will be nil\n\n:constructors {[param-types] [super-param-types], ...}\n\nBy default, constructors are created for the generated class which\nmatch the signature(s) of the constructors for the superclass. This\nparameter may be used to explicitly specify constructors, each entry\nproviding a mapping from a constructor signature to a superclass\nconstructor signature. When you supply this, you must supply an :init\nspecifier. \n\n:post-init name\n\nIf supplied, names a function that will be called with the object as\nthe first argument, followed by the arguments to the constructor.\nIt will be called every time an object of this class is created,\nimmediately after all the inherited constructors have completed.\nIt's return value is ignored.\n\n:methods [ [name [param-types] return-type], ...]\n\nThe generated class automatically defines all of the non-private\nmethods of its superclasses/interfaces. This parameter can be used\nto specify the signatures of additional methods of the generated\nclass. Static methods can be specified with #^{:static true} in the\nsignature's metadata. Do not repeat superclass/interface signatures\nhere.\n\n:main boolean\n\nIf supplied and true, a static public main function will be generated. It will\npass each string of the String[] argument as a separate argument to\na function called (str prefix main).\n\n:factory name\n\nIf supplied, a (set of) public static factory function(s) will be\ncreated with the given name, and the same signature(s) as the\nconstructor(s).\n\n:state name\n\nIf supplied, a public final instance field with the given name will be\ncreated. You must supply an :init function in order to provide a\nvalue for the state. Note that, though final, the state can be a ref\nor agent, supporting the creation of Java objects with transactional\nor asynchronous mutation semantics.\n\n:exposes {protected-field-name {:get name :set name}, ...}\n\nSince the implementations of the methods of the generated class\noccur in Clojure functions, they have no access to the inherited\nprotected fields of the superclass. This parameter can be used to\ngenerate public getter/setter methods exposing the protected field(s)\nfor use in the implementation.\n\n:exposes-methods {super-method-name exposed-name, ...}\n\nIt is sometimes necessary to call the superclass' implementation of an\noverridden method.  Those methods may be exposed and referred in \nthe new method implementation by a local name.\n\n:prefix string\n\nDefault: \"-\" Methods called e.g. Foo will be looked up in vars called\nprefixFoo in the implementing ns.\n\n:impl-ns name\n\nDefault: the name of the current ns. Implementations of methods will be \nlooked up in this namespace.\n\n:load-impl-ns boolean\n\nDefault: true. Causes the static initializer for the generated class\nto reference the load code for the implementing namespace. Should be\ntrue when implementing-ns is the default, false if you intend to\nload the code via some other method.",
   :name "gen-class"}
  {:source-url
   "http://github.com/clojure/clojure/blob/6109d41a975bf24b17681342591116a9897e4a27/src/clj/clojure/genclass.clj#L635",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/gen-interface",
   :namespace "clojure.core",
   :line 635,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/genclass.clj",
   :var-type "macro",
   :arglists ([& options]),
   :doc
   "When compiling, generates compiled bytecode for an interface with\n the given package-qualified :name (which, as all names in these\n parameters, can be a string or symbol), and writes the .class file\n to the *compile-path* directory.  When not compiling, does nothing.\n\n In all subsequent sections taking types, the primitive types can be\n referred to by their Java names (int, float etc), and classes in the\n java.lang package can be used without a package qualifier. All other\n classes must be fully qualified.\n\n Options should be a set of key/value pairs, all except for :name are\n optional:\n\n :name aname\n\n The package-qualified name of the class to be generated\n\n :extends [interface ...]\n\n One or more interfaces, which will be extended by this interface.\n\n :methods [ [name [param-types] return-type], ...]\n\n This parameter is used to specify the signatures of the methods of\n the generated interface.  Do not repeat superinterface signatures\n here.",
   :name "gen-interface"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L393",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/gensym",
   :namespace "clojure.core",
   :line 393,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([] [prefix-string]),
   :doc
   "Returns a new symbol with a unique name. If a prefix string is\nsupplied, the name is prefix# where # is some unique number. If\nprefix is not supplied, the prefix is 'G__'.",
   :name "gensym"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L977",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/get",
   :namespace "clojure.core",
   :line 977,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([map key] [map key not-found]),
   :doc
   "Returns the value mapped to key, not-found or nil if key not present.",
   :name "get"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4186",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/get-in",
   :namespace "clojure.core",
   :line 4186,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([m ks]),
   :doc
   "returns the value in a nested associative structure, where ks is a sequence of keys",
   :name "get-in"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1172",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/get-method",
   :namespace "clojure.core",
   :line 1172,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([multifn dispatch-val]),
   :doc
   "Given a multimethod and a dispatch value, returns the dispatch fn\nthat would apply to that value, or nil if none apply and no default",
   :name "get-method"}
  {:source-url
   "http://github.com/clojure/clojure/blob/040f083efc16dd830a4508a35a04465e3e5677d3/src/clj/clojure/core_proxy.clj#L250",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/get-proxy-class",
   :namespace "clojure.core",
   :line 250,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/core_proxy.clj",
   :var-type "function",
   :arglists ([& bases]),
   :doc
   "Takes an optional single class followed by zero or more\ninterfaces. If not supplied class defaults to Object.  Creates an\nreturns an instance of a proxy class derived from the supplied\nclasses. The resulting value is cached and used for any subsequent\nrequests for the same class set. Returns a Class object.",
   :name "get-proxy-class"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1245",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/get-thread-bindings",
   :namespace "clojure.core",
   :line 1245,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([]),
   :doc
   "Get a map with the Var/value pairs which is currently in effect for the\ncurrent thread.",
   :name "get-thread-bindings"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1499",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/get-validator",
   :namespace "clojure.core",
   :line 1499,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([iref]),
   :doc "Gets the validator-fn for a var/ref/agent/atom.",
   :name "get-validator"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3483",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/hash",
   :namespace "clojure.core",
   :line 3483,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Returns the hash code of its argument",
   :name "hash"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L274",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/hash-map",
   :namespace "clojure.core",
   :line 274,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([] [& keyvals]),
   :doc
   "keyval => key val\nReturns a new hash map with supplied mappings.",
   :name "hash-map"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L281",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/hash-set",
   :namespace "clojure.core",
   :line 281,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([] [& keys]),
   :doc "Returns a new hash set with supplied keys.",
   :name "hash-set"}
  {:source-url nil,
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/identical?",
   :namespace "clojure.core",
   :var-type "function",
   :arglists ([x y]),
   :doc "Tests if 2 arguments are the same object",
   :name "identical?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L944",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/identity",
   :namespace "clojure.core",
   :line 944,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Returns its argument.",
   :name "identity"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1191",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/if-let",
   :namespace "clojure.core",
   :line 1191,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([bindings then] [bindings then else & oldform]),
   :doc
   "bindings => binding-form test\n\nIf test is true, evaluates then with binding-form bound to the value of \ntest, if not, yields else",
   :name "if-let"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L532",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/if-not",
   :namespace "clojure.core",
   :line 532,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([test then] [test then else]),
   :doc
   "Evaluates test. If logical false, evaluates and returns then expr, \notherwise else expr, if supplied, else nil.",
   :name "if-not"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4229",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/ifn?",
   :namespace "clojure.core",
   :line 4229,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc
   "Returns true if x implements IFn. Note that many data structures\n(e.g. sets and maps) implement IFn",
   :name "ifn?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2163",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/import",
   :namespace "clojure.core",
   :line 2163,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([& import-symbols-or-lists]),
   :doc
   "import-list => (package-symbol class-name-symbols*)\n\nFor each name in class-name-symbols, adds a mapping from name to the\nclass named by package.name to the current namespace. Use :import in the ns\nmacro in preference to calling this directly.",
   :name "import"}
  {:source-url nil,
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/in-ns",
   :namespace "clojure.core",
   :var-type "function",
   :arglists ([name]),
   :doc
   "Sets *ns* to the namespace named by the symbol, creating it if needed.",
   :name "in-ns"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L641",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/inc",
   :namespace "clojure.core",
   :line 641,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Returns a number one greater than num.",
   :name "inc"}
  {:source-url
   "http://github.com/clojure/clojure/blob/040f083efc16dd830a4508a35a04465e3e5677d3/src/clj/clojure/core_proxy.clj#L269",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/init-proxy",
   :namespace "clojure.core",
   :line 269,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/core_proxy.clj",
   :var-type "function",
   :arglists ([proxy mappings]),
   :doc
   "Takes a proxy instance and a map of strings (which must\ncorrespond to methods of the proxy superclass/superinterfaces) to\nfns (which must take arguments matching the corresponding method,\nplus an additional (explicit) first arg corresponding to this, and\nsets the proxy's fn map.",
   :name "init-proxy"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L107",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/instance?",
   :namespace "clojure.core",
   :line 107,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([c x]),
   :doc
   "Evaluates x and tests if it is an instance of the class\nc. Returns true or false",
   :name "instance?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L611",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/int",
   :namespace "clojure.core",
   :line 611,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Coerce to int",
   :name "int"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3576",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/int-array",
   :namespace "clojure.core",
   :line 3576,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([size-or-seq] [size init-val-or-seq]),
   :doc "Creates an array of ints",
   :name "int-array"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2257",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/integer?",
   :namespace "clojure.core",
   :line 2257,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([n]),
   :doc "Returns true if n is an integer",
   :name "integer?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2778",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/interleave",
   :namespace "clojure.core",
   :line 2778,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([c1 c2] [c1 c2 & colls]),
   :doc
   "Returns a lazy seq of the first item in each coll, then the second etc.",
   :name "interleave"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4295",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/intern",
   :namespace "clojure.core",
   :line 4295,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([ns name] [ns name val]),
   :doc
   "Finds or creates a var named by the symbol name in the namespace\nns (which can be a symbol or a namespace), setting its root binding\nto val if supplied. The namespace must exist. The var will adopt any\nmetadata from the name symbol.  Returns the var.",
   :name "intern"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3487",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/interpose",
   :namespace "clojure.core",
   :line 3487,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([sep coll]),
   :doc "Returns a lazy seq of the elements of coll separated by sep",
   :name "interpose"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4667",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/into",
   :namespace "clojure.core",
   :line 4667,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([to from]),
   :doc
   "Returns a new coll consisting of to-coll with all of the items of\nfrom-coll conjoined.",
   :name "into"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2180",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/into-array",
   :namespace "clojure.core",
   :line 2180,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([aseq] [type aseq]),
   :doc
   "Returns an array with components set to the values in aseq. The array's\ncomponent type is type if provided, or the type of the first value in\naseq if present, or Object. All values in aseq must be compatible with\nthe component type. Class objects for the primitive types can be obtained\nusing, e.g., Integer/TYPE.",
   :name "into-array"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3610",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/ints",
   :namespace "clojure.core",
   :line 3610,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([xs]),
   :doc "Casts to int[]",
   :name "ints"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1590",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/io!",
   :namespace "clojure.core",
   :line 1590,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([& body]),
   :doc
   "If an io! block occurs in a transaction, throws an\nIllegalStateException, else runs body in an implicit do. If the\nfirst expression in body is a literal string, will use that as the\nexception message.",
   :name "io!"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3697",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/isa?",
   :namespace "clojure.core",
   :line 3697,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([child parent] [h child parent]),
   :doc
   "Returns true if (= child parent), or child is directly or indirectly derived from\nparent, either via a Java type inheritance relationship or a\nrelationship established via derive. h must be a hierarchy obtained\nfrom make-hierarchy, if not supplied defaults to the global\nhierarchy",
   :name "isa?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1893",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/iterate",
   :namespace "clojure.core",
   :line 1893,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([f x]),
   :doc
   "Returns a lazy sequence of x, (f x), (f (f x)) etc. f must be free of side-effects",
   :name "iterate"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3845",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/iterator-seq",
   :namespace "clojure.core",
   :line 3845,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([iter]),
   :doc
   "Returns a seq on a java.util.Iterator. Note that most collections\nproviding iterators implement Iterable and thus support seq directly.",
   :name "iterator-seq"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1633",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/juxt",
   :namespace "clojure.core",
   :line 1633,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([f] [f g] [f g h] [f g h & fs]),
   :doc
   "Alpha - name subject to change.\nTakes a set of functions and returns a fn that is the juxtaposition\nof those fns.  The returned fn takes a variable number of args, and\nreturns a vector containing the result of applying each fn to the\nargs (left-to-right).\n((juxt a b c) x) => [(a x) (b x) (c x)]",
   :name "juxt"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1033",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/key",
   :namespace "clojure.core",
   :line 1033,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([e]),
   :doc "Returns the key of the map entry.",
   :name "key"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1025",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/keys",
   :namespace "clojure.core",
   :line 1025,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([map]),
   :doc "Returns a sequence of the map's keys.",
   :name "keys"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L386",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/keyword",
   :namespace "clojure.core",
   :line 386,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([name] [ns name]),
   :doc
   "Returns a Keyword with the given namespace and name.  Do not use :\nin the keyword strings, it will be added automatically.",
   :name "keyword"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L376",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/keyword?",
   :namespace "clojure.core",
   :line 376,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Return true if x is a Keyword",
   :name "keyword?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L189",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/last",
   :namespace "clojure.core",
   :line 189,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc "Return the last item in coll, in linear time",
   :name "last"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3004",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/lazy-cat",
   :namespace "clojure.core",
   :line 3004,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([& colls]),
   :doc
   "Expands to code which yields a lazy sequence of the concatenation\nof the supplied colls.  Each coll expr is not evaluated until it is\nneeded. \n\n(lazy-cat xs ys zs) === (concat (lazy-seq xs) (lazy-seq ys) (lazy-seq zs))",
   :name "lazy-cat"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L454",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/lazy-seq",
   :namespace "clojure.core",
   :line 454,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([& body]),
   :doc
   "Takes a body of expressions that returns an ISeq or nil, and yields\na Seqable object that will invoke the body only the first time seq\nis called, and will cache the result and return it on all subsequent\nseq calls.",
   :name "lazy-seq"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2902",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/let",
   :namespace "clojure.core",
   :line 2902,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([bindings & body]),
   :doc
   "Evaluates the exprs in a lexical context in which the symbols in\nthe binding-forms are bound to their respective init-exprs or parts\ntherein.",
   :name "let"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4531",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/letfn",
   :namespace "clojure.core",
   :line 4531,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([fnspecs & body]),
   :doc
   "Takes a vector of function specs and a body, and generates a set of\nbindings of functions to their names. All of the names are available\nin all of the definitions of the functions, as well as the body.\n\nfnspec ==> (fname [params*] exprs) or (fname ([params*] exprs)+)",
   :name "letfn"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1954",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/line-seq",
   :namespace "clojure.core",
   :line 1954,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([rdr]),
   :doc
   "Returns the lines of text from rdr as a lazy sequence of strings.\nrdr must implement java.io.BufferedReader.",
   :name "line-seq"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L16",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/list",
   :namespace "clojure.core",
   :line 16,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([& items]),
   :doc "Creates a new list containing the items.",
   :name "list"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L422",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/list*",
   :namespace "clojure.core",
   :line 422,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists
   ([args] [a args] [a b args] [a b c args] [a b c d & more]),
   :doc
   "Creates a new list containing the items prepended to the rest, the\nlast of which will be treated as a sequence.",
   :name "list*"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4221",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/list?",
   :namespace "clojure.core",
   :line 4221,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Returns true if x implements IPersistentList",
   :name "list?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4154",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/load",
   :namespace "clojure.core",
   :line 4154,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([& paths]),
   :doc
   "Loads Clojure code from resources in classpath. A path is interpreted as\nclasspath-relative if it begins with a slash or relative to the root\ndirectory for the current namespace otherwise.",
   :name "load"}
  {:source-url nil,
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/load-file",
   :namespace "clojure.core",
   :var-type "function",
   :arglists ([name]),
   :doc
   "Sequentially read and evaluate the set of forms contained in the file.",
   :name "load-file"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2621",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/load-reader",
   :namespace "clojure.core",
   :line 2621,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([rdr]),
   :doc
   "Sequentially read and evaluate the set of forms contained in the\nstream/file",
   :name "load-reader"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2626",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/load-string",
   :namespace "clojure.core",
   :line 2626,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([s]),
   :doc
   "Sequentially read and evaluate the set of forms contained in the\nstring",
   :name "load-string"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4150",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/loaded-libs",
   :namespace "clojure.core",
   :line 4150,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([]),
   :doc
   "Returns a sorted set of symbols naming the currently loaded libs",
   :name "loaded-libs"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1061",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/locking",
   :namespace "clojure.core",
   :line 1061,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([x & body]),
   :doc
   "Executes exprs in an implicit do, while holding the monitor of x.\nWill release the monitor of x in all circumstances.",
   :name "locking"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2210",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/long",
   :namespace "clojure.core",
   :line 2210,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Coerce to long",
   :name "long"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3583",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/long-array",
   :namespace "clojure.core",
   :line 3583,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([size-or-seq] [size init-val-or-seq]),
   :doc "Creates an array of longs",
   :name "long-array"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3618",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/longs",
   :namespace "clojure.core",
   :line 3618,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([xs]),
   :doc "Casts to long[]",
   :name "longs"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2967",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/loop",
   :namespace "clojure.core",
   :line 2967,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([bindings & body]),
   :doc
   "Evaluates the exprs in a lexical context in which the symbols in\nthe binding-forms are bound to their respective init-exprs or parts\ntherein. Acts as a recur target.",
   :name "loop"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2577",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/macroexpand",
   :namespace "clojure.core",
   :line 2577,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([form]),
   :doc
   "Repeatedly calls macroexpand-1 on form until it no longer\nrepresents a macro form, then returns it.  Note neither\nmacroexpand-1 nor macroexpand expand macros in subforms.",
   :name "macroexpand"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2571",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/macroexpand-1",
   :namespace "clojure.core",
   :line 2571,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([form]),
   :doc
   "If form represents a macro form, returns its expansion,\nelse returns form.",
   :name "macroexpand-1"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2543",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/make-array",
   :namespace "clojure.core",
   :line 2543,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([type len] [type dim & more-dims]),
   :doc
   "Creates and returns an array of instances of the specified class of\nthe specified dimension(s).  Note that a class object is required.\nClass objects can be obtained by using their imported or\nfully-qualified name.  Class objects for the primitive types can be\nobtained using, e.g., Integer/TYPE.",
   :name "make-array"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3669",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/make-hierarchy",
   :namespace "clojure.core",
   :line 3669,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([]),
   :doc "Creates a hierarchy object for use with derive, isa? etc.",
   :name "make-hierarchy"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1746",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/map",
   :namespace "clojure.core",
   :line 1746,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([f coll] [f c1 c2] [f c1 c2 c3] [f c1 c2 c3 & colls]),
   :doc
   "Returns a lazy sequence consisting of the result of applying f to the\nset of first items of each coll, followed by applying f to the set\nof second items in each coll, until any one of the colls is\nexhausted.  Any remaining items in other colls are ignored. Function\nf should accept number-of-colls arguments.",
   :name "map"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L128",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/map?",
   :namespace "clojure.core",
   :line 128,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Return true if x implements IPersistentMap",
   :name "map?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1783",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/mapcat",
   :namespace "clojure.core",
   :line 1783,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([f & colls]),
   :doc
   "Returns the result of applying concat to the result of applying map\nto f and colls.  Thus function f should return a collection.",
   :name "mapcat"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L772",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/max",
   :namespace "clojure.core",
   :line 772,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x] [x y] [x y & more]),
   :doc "Returns the greatest of the nums.",
   :name "max"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3364",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/max-key",
   :namespace "clojure.core",
   :line 3364,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([k x] [k x y] [k x y & more]),
   :doc "Returns the x for which (k x), a number, is greatest.",
   :name "max-key"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2445",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/memfn",
   :namespace "clojure.core",
   :line 2445,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([name & args]),
   :doc
   "Expands into code that creates a fn that expects to be passed an\nobject and any args and calls the named instance method on the\nobject passing the args. Use when you want to treat a Java method as\na first-class fn.",
   :name "memfn"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4318",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/memoize",
   :namespace "clojure.core",
   :line 4318,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([f]),
   :doc
   "Returns a memoized version of a referentially transparent function. The\nmemoized version of the function keeps a cache of the mapping from arguments\nto results and, when calls with the same arguments are repeated often, has\nhigher performance at the expense of higher memory use.",
   :name "memoize"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1916",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/merge",
   :namespace "clojure.core",
   :line 1916,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([& maps]),
   :doc
   "Returns a map that consists of the rest of the maps conj-ed onto\nthe first.  If a key occurs in more than one map, the mapping from\nthe latter (left-to-right) will be the mapping in the result.",
   :name "merge"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1924",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/merge-with",
   :namespace "clojure.core",
   :line 1924,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([f & maps]),
   :doc
   "Returns a map that consists of the rest of the maps conj-ed onto\nthe first.  If a key occurs in more than one map, the mapping(s)\nfrom the latter (left-to-right) will be combined with the mapping in\nthe result by calling (f val-in-result val-in-latter).",
   :name "merge-with"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L154",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/meta",
   :namespace "clojure.core",
   :line 154,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([obj]),
   :doc
   "Returns the metadata of obj, returns nil if there is no metadata.",
   :name "meta"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1168",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/methods",
   :namespace "clojure.core",
   :line 1168,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([multifn]),
   :doc
   "Given a multimethod, returns a map of dispatch values -> dispatch fns",
   :name "methods"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L779",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/min",
   :namespace "clojure.core",
   :line 779,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x] [x y] [x y & more]),
   :doc "Returns the least of the nums.",
   :name "min"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3371",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/min-key",
   :namespace "clojure.core",
   :line 3371,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([k x] [k x y] [k x y & more]),
   :doc "Returns the x for which (k x), a number, is least.",
   :name "min-key"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2266",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/mod",
   :namespace "clojure.core",
   :line 2266,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([num div]),
   :doc "Modulus of num and div. Truncates toward negative infinity.",
   :name "mod"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1049",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/name",
   :namespace "clojure.core",
   :line 1049,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Returns the name String of a symbol or keyword.",
   :name "name"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1055",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/namespace",
   :namespace "clojure.core",
   :line 1055,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc
   "Returns the namespace String of a symbol or keyword, or nil if not present.",
   :name "namespace"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L845",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/neg?",
   :namespace "clojure.core",
   :line 845,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Returns true if num is less than zero, else false",
   :name "neg?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2338",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/newline",
   :namespace "clojure.core",
   :line 2338,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([]),
   :doc
   "Writes a newline to the output stream that is the current value of\n*out*",
   :name "newline"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L47",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/next",
   :namespace "clojure.core",
   :line 47,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc
   "Returns a seq of the items after the first. Calls seq on its\nargument.  If there are no more items, returns nil.",
   :name "next"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L83",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/nfirst",
   :namespace "clojure.core",
   :line 83,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Same as (next (first x))",
   :name "nfirst"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L336",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/nil?",
   :namespace "clojure.core",
   :line 336,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Returns true if x is nil, false otherwise.",
   :name "nil?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L93",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/nnext",
   :namespace "clojure.core",
   :line 93,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Same as (next (next x))",
   :name "nnext"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L351",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/not",
   :namespace "clojure.core",
   :line 351,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Returns true if x is logical false, false otherwise.",
   :name "not"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1724",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/not-any?",
   :namespace "clojure.core",
   :line 1724,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([pred coll]),
   :doc
   "Returns false if (pred x) is logical true for any x in coll,\nelse true.",
   :name "not-any?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3676",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/not-empty",
   :namespace "clojure.core",
   :line 3676,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc "If coll is empty, returns nil, else coll",
   :name "not-empty"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1708",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/not-every?",
   :namespace "clojure.core",
   :line 1708,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([pred coll]),
   :doc
   "Returns false if (pred x) is logical true for every x in\ncoll, else true.",
   :name "not-every?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L557",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/not=",
   :namespace "clojure.core",
   :line 557,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x] [x y] [x y & more]),
   :doc "Same as (not (= obj1 obj2))",
   :name "not="}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3879",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/ns",
   :namespace "clojure.core",
   :line 3879,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([name & references]),
   :doc
   "Sets *ns* to the namespace named by name (unevaluated), creating it\nif needed.  references can be zero or more of: (:refer-clojure ...)\n(:require ...) (:use ...) (:import ...) (:load ...) (:gen-class)\nwith the syntax of refer-clojure/require/use/import/load/gen-class\nrespectively, except the arguments are unevaluated and need not be\nquoted. (:gen-class ...), when supplied, defaults to :name\ncorresponding to the ns name, :main true, :impl-ns same as ns, and\n:init-impl-ns true. All options of gen-class are\nsupported. The :gen-class directive is ignored when not\ncompiling. If :gen-class is not supplied, when compiled only an\nnsname__init.class will be generated. If :refer-clojure is not used, a\ndefault (refer 'clojure) is used.  Use of ns is preferred to\nindividual calls to in-ns/require/use/import:\n\n(ns foo.bar\n  (:refer-clojure :exclude [ancestors printf])\n  (:require (clojure.contrib sql sql.tests))\n  (:use (my.lib this that))\n  (:import (java.util Date Timer Random)\n            (java.sql Connection Statement)))",
   :name "ns"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2761",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/ns-aliases",
   :namespace "clojure.core",
   :line 2761,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([ns]),
   :doc "Returns a map of the aliases for the namespace.",
   :name "ns-aliases"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2703",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/ns-imports",
   :namespace "clojure.core",
   :line 2703,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([ns]),
   :doc "Returns a map of the import mappings for the namespace.",
   :name "ns-imports"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2745",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/ns-interns",
   :namespace "clojure.core",
   :line 2745,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([ns]),
   :doc "Returns a map of the intern mappings for the namespace.",
   :name "ns-interns"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2680",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/ns-map",
   :namespace "clojure.core",
   :line 2680,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([ns]),
   :doc "Returns a map of all the mappings for the namespace.",
   :name "ns-map"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2675",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/ns-name",
   :namespace "clojure.core",
   :line 2675,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([ns]),
   :doc "Returns the name of the namespace, a symbol.",
   :name "ns-name"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2694",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/ns-publics",
   :namespace "clojure.core",
   :line 2694,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([ns]),
   :doc
   "Returns a map of the public intern mappings for the namespace.",
   :name "ns-publics"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2737",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/ns-refers",
   :namespace "clojure.core",
   :line 2737,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([ns]),
   :doc "Returns a map of the refer mappings for the namespace.",
   :name "ns-refers"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2819",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/ns-resolve",
   :namespace "clojure.core",
   :line 2819,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([ns sym]),
   :doc
   "Returns the var or Class to which a symbol will be resolved in the\nnamespace, else nil.  Note that if the symbol is fully qualified,\nthe var/Class to which it resolves need not be present in the\nnamespace.",
   :name "ns-resolve"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2766",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/ns-unalias",
   :namespace "clojure.core",
   :line 2766,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([ns sym]),
   :doc "Removes the alias for the symbol from the namespace.",
   :name "ns-unalias"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2685",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/ns-unmap",
   :namespace "clojure.core",
   :line 2685,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([ns sym]),
   :doc "Removes the mappings for the symbol from the namespace.",
   :name "ns-unmap"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L617",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/nth",
   :namespace "clojure.core",
   :line 617,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll index] [coll index not-found]),
   :doc
   "Returns the value at the index. get returns nil if index out of\nbounds, nth throws an exception unless not-found is supplied.  nth\nalso works for strings, Java arrays, regex Matchers and Lists, and,\nin O(n) time, for sequences.",
   :name "nth"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2836",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/nthnext",
   :namespace "clojure.core",
   :line 2836,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll n]),
   :doc "Returns the nth next of coll, (seq coll) when n is 0.",
   :name "nthnext"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2204",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/num",
   :namespace "clojure.core",
   :line 2204,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Coerce to Number",
   :name "num"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2252",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/number?",
   :namespace "clojure.core",
   :line 2252,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Returns true if x is a Number",
   :name "number?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L923",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/odd?",
   :namespace "clojure.core",
   :line 923,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([n]),
   :doc
   "Returns true if n is odd, throws an exception if n is not an integer",
   :name "odd?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L588",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/or",
   :namespace "clojure.core",
   :line 588,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([] [x] [x & next]),
   :doc
   "Evaluates exprs one at a time, from left to right. If a form\nreturns a logical true value, or returns that value and doesn't\nevaluate any of the other expressions, otherwise it returns the\nvalue of the last expression. (or) returns nil.",
   :name "or"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3717",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/parents",
   :namespace "clojure.core",
   :line 3717,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([tag] [h tag]),
   :doc
   "Returns the immediate parents of tag, either via a Java type\ninheritance relationship or a relationship established via derive. h\nmust be a hierarchy obtained from make-hierarchy, if not supplied\ndefaults to the global hierarchy",
   :name "parents"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1670",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/partial",
   :namespace "clojure.core",
   :line 1670,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists
   ([f arg1]
    [f arg1 arg2]
    [f arg1 arg2 arg3]
    [f arg1 arg2 arg3 & more]),
   :doc
   "Takes a function f and fewer than the normal arguments to f, and\nreturns a fn that takes a variable number of additional args. When\ncalled, the returned function calls f with args + additional args.",
   :name "partial"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1991",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/partition",
   :namespace "clojure.core",
   :line 1991,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([n coll] [n step coll] [n step pad coll]),
   :doc
   "Returns a lazy sequence of lists of n items each, at offsets step\napart. If step is not supplied, defaults to n, i.e. the partitions\ndo not overlap. If a pad collection is supplied, use its elements as\nnecessary to complete last partition upto n items. In case there are\nnot enough padding elements, return a partition with less than n items.",
   :name "partition"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4520",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/pcalls",
   :namespace "clojure.core",
   :line 4520,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([& fns]),
   :doc
   "Executes the no-arg fns in parallel, returning a lazy sequence of\ntheir values",
   :name "pcalls"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L955",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/peek",
   :namespace "clojure.core",
   :line 955,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc
   "For a list or queue, same as first, for a vector, same as, but much\nmore efficient than, last. If the collection is empty, returns nil.",
   :name "peek"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4609",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/persistent!",
   :namespace "clojure.core",
   :line 4609,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc
   "Alpha - subject to change.\nReturns a new, persistent version of the transient collection, in\nconstant time. The transient collection cannot be used after this\ncall, any such use will throw an exception.",
   :name "persistent!"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4497",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/pmap",
   :namespace "clojure.core",
   :line 4497,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([f coll] [f coll & colls]),
   :doc
   "Like map, except f is applied in parallel. Semi-lazy in that the\nparallel computation stays ahead of the consumption, but doesn't\nrealize the entire result unless required. Only useful for\ncomputationally intensive functions where the time of f dominates\nthe coordination overhead.",
   :name "pmap"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L960",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/pop",
   :namespace "clojure.core",
   :line 960,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc
   "For a list or queue, returns a new list/queue without the first\nitem, for a vector, returns a new vector without the last item. If\nthe collection is empty, throws an exception.  Note - not the same\nas next/butlast.",
   :name "pop"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4646",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/pop!",
   :namespace "clojure.core",
   :line 4646,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc
   "Alpha - subject to change.\nRemoves the last item from a transient vector. If\nthe collection is empty, throws an exception. Returns coll",
   :name "pop!"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1239",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/pop-thread-bindings",
   :namespace "clojure.core",
   :line 1239,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([]),
   :doc
   "Pop one set of bindings pushed with push-binding before. It is an error to\npop bindings without pushing before.",
   :name "pop-thread-bindings"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L839",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/pos?",
   :namespace "clojure.core",
   :line 839,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Returns true if num is greater than zero, else false",
   :name "pos?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2325",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/pr",
   :namespace "clojure.core",
   :line 2325,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([] [x] [x & more]),
   :doc
   "Prints the object(s) to the output stream that is the current value\nof *out*.  Prints the object(s), separated by spaces if there is\nmore than one.  By default, pr and prn print in a way that objects\ncan be read by the reader",
   :name "pr"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3121",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/pr-str",
   :namespace "clojure.core",
   :line 3121,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([& xs]),
   :doc "pr to a string, returning it",
   :name "pr-str"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1162",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/prefer-method",
   :namespace "clojure.core",
   :line 1162,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([multifn dispatch-val-x dispatch-val-y]),
   :doc
   "Causes the multimethod to prefer matches of dispatch-val-x over dispatch-val-y \nwhen there is a conflict",
   :name "prefer-method"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1177",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/prefers",
   :namespace "clojure.core",
   :line 1177,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([multifn]),
   :doc
   "Given a multimethod, returns a map of preferred value -> set of other values",
   :name "prefers"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2360",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/print",
   :namespace "clojure.core",
   :line 2360,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([& more]),
   :doc
   "Prints the object(s) to the output stream that is the current value\nof *out*.  print and println produce output for human consumption.",
   :name "print"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3281",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/print-namespace-doc",
   :namespace "clojure.core",
   :line 3281,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([nspace]),
   :doc "Print the documentation string of a Namespace.",
   :name "print-namespace-doc"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3135",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/print-str",
   :namespace "clojure.core",
   :line 3135,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([& xs]),
   :doc "print to a string, returning it",
   :name "print-str"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3863",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/printf",
   :namespace "clojure.core",
   :line 3863,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([fmt & args]),
   :doc "Prints formatted output, as per format",
   :name "printf"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2367",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/println",
   :namespace "clojure.core",
   :line 2367,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([& more]),
   :doc "Same as print followed by (newline)",
   :name "println"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3142",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/println-str",
   :namespace "clojure.core",
   :line 3142,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([& xs]),
   :doc "println to a string, returning it",
   :name "println-str"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2352",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/prn",
   :namespace "clojure.core",
   :line 2352,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([& more]),
   :doc
   "Same as pr followed by (newline). Observes *flush-on-newline*",
   :name "prn"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3128",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/prn-str",
   :namespace "clojure.core",
   :line 3128,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([& xs]),
   :doc "prn to a string, returning it",
   :name "prn-str"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4577",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/promise",
   :namespace "clojure.core",
   :line 4577,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([]),
   :doc
   "Alpha - subject to change.\nReturns a promise object that can be read with deref/@, and set,\nonce only, with deliver. Calls to deref/@ prior to delivery will\nblock. All subsequent derefs will return the same delivered value\nwithout blocking.",
   :name "promise"}
  {:source-url
   "http://github.com/clojure/clojure/blob/040f083efc16dd830a4508a35a04465e3e5677d3/src/clj/clojure/core_proxy.clj#L295",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/proxy",
   :namespace "clojure.core",
   :line 295,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/core_proxy.clj",
   :var-type "macro",
   :arglists ([class-and-interfaces args & fs]),
   :doc
   "class-and-interfaces - a vector of class names\n\nargs - a (possibly empty) vector of arguments to the superclass\nconstructor.\n\nf => (name [params*] body) or\n(name ([params*] body) ([params+] body) ...)\n\nExpands to code which creates a instance of a proxy class that\nimplements the named class/interface(s) by calling the supplied\nfns. A single class, if provided, must be first. If not provided it\ndefaults to Object.\n\nThe interfaces names must be valid interface types. If a method fn\nis not provided for a class method, the superclass methd will be\ncalled. If a method fn is not provided for an interface method, an\nUnsupportedOperationException will be thrown should it be\ncalled. Method fns are closures and can capture the environment in\nwhich proxy is called. Each method fn takes an additional implicit\nfirst arg, which is bound to 'this. Note that while method fns can\nbe provided to override protected methods, they have no other access\nto protected members, nor to super, as these capabilities cannot be\nproxied.",
   :name "proxy"}
  {:source-url
   "http://github.com/clojure/clojure/blob/040f083efc16dd830a4508a35a04465e3e5677d3/src/clj/clojure/core_proxy.clj#L290",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/proxy-mappings",
   :namespace "clojure.core",
   :line 290,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/core_proxy.clj",
   :var-type "function",
   :arglists ([proxy]),
   :doc "Takes a proxy instance and returns the proxy's fn map.",
   :name "proxy-mappings"}
  {:source-url
   "http://github.com/clojure/clojure/blob/040f083efc16dd830a4508a35a04465e3e5677d3/src/clj/clojure/core_proxy.clj#L354",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/proxy-super",
   :namespace "clojure.core",
   :line 354,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/core_proxy.clj",
   :var-type "macro",
   :arglists ([meth & args]),
   :doc
   "Use to call a superclass method in the body of a proxy method. \nNote, expansion captures 'this",
   :name "proxy-super"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1223",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/push-thread-bindings",
   :namespace "clojure.core",
   :line 1223,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([bindings]),
   :doc
   "WARNING: This is a low-level function. Prefer high-level macros like\nbinding where ever possible.\n\nTakes a map of Var/value pairs. Binds each Var to the associated value for\nthe current thread. Each call *MUST* be accompanied by a matching call to\npop-thread-bindings wrapped in a try-finally!\n\n    (push-thread-bindings bindings)\n    (try\n      ...\n      (finally\n        (pop-thread-bindings)))",
   :name "push-thread-bindings"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4525",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/pvalues",
   :namespace "clojure.core",
   :line 4525,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([& exprs]),
   :doc
   "Returns a lazy sequence of the values of the exprs, which are\nevaluated in parallel",
   :name "pvalues"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L851",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/quot",
   :namespace "clojure.core",
   :line 851,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([num div]),
   :doc "quot[ient] of dividing numerator by denominator.",
   :name "quot"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3226",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/rand",
   :namespace "clojure.core",
   :line 3226,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([] [n]),
   :doc
   "Returns a random floating point number between 0 (inclusive) and\nn (default 1) (exclusive).",
   :name "rand"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3232",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/rand-int",
   :namespace "clojure.core",
   :line 3232,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([n]),
   :doc
   "Returns a random integer between 0 (inclusive) and n (exclusive).",
   :name "rand-int"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1897",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/range",
   :namespace "clojure.core",
   :line 1897,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([end] [start end] [start end step]),
   :doc
   "Returns a lazy seq of nums from start (inclusive) to end\n(exclusive), by step, where start defaults to 0 and step to 1.",
   :name "range"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2274",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/ratio?",
   :namespace "clojure.core",
   :line 2274,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([n]),
   :doc "Returns true if n is a Ratio",
   :name "ratio?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L861",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/rationalize",
   :namespace "clojure.core",
   :line 861,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([num]),
   :doc "returns the rational value of num",
   :name "rationalize"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3215",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/re-find",
   :namespace "clojure.core",
   :line 3215,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([m] [re s]),
   :doc
   "Returns the next regex match, if any, of string to pattern, using\njava.util.regex.Matcher.find().  Uses re-groups to return the\ngroups.",
   :name "re-find"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3181",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/re-groups",
   :namespace "clojure.core",
   :line 3181,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([m]),
   :doc
   "Returns the groups from the most recent match/find. If there are no\nnested groups, returns a string of the entire match. If there are\nnested groups, returns a vector of the groups, the first element\nbeing the entire match.",
   :name "re-groups"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3174",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/re-matcher",
   :namespace "clojure.core",
   :line 3174,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([re s]),
   :doc
   "Returns an instance of java.util.regex.Matcher, for use, e.g. in\nre-find.",
   :name "re-matcher"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3205",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/re-matches",
   :namespace "clojure.core",
   :line 3205,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([re s]),
   :doc
   "Returns the match, if any, of string to pattern, using\njava.util.regex.Matcher.matches().  Uses re-groups to return the\ngroups.",
   :name "re-matches"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3166",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/re-pattern",
   :namespace "clojure.core",
   :line 3166,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([s]),
   :doc
   "Returns an instance of java.util.regex.Pattern, for use, e.g. in\nre-matcher.",
   :name "re-pattern"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3195",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/re-seq",
   :namespace "clojure.core",
   :line 3195,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([re s]),
   :doc
   "Returns a lazy sequence of successive matches of pattern in string,\nusing java.util.regex.Matcher.find(), each such match processed with\nre-groups.",
   :name "re-seq"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2374",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/read",
   :namespace "clojure.core",
   :line 2374,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists
   ([]
    [stream]
    [stream eof-error? eof-value]
    [stream eof-error? eof-value recursive?]),
   :doc
   "Reads the next object from stream, which must be an instance of\njava.io.PushbackReader or some derivee.  stream defaults to the\ncurrent value of *in* .",
   :name "read"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2387",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/read-line",
   :namespace "clojure.core",
   :line 2387,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([]),
   :doc
   "Reads the next line from stream that is the current value of *in* .",
   :name "read-line"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2394",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/read-string",
   :namespace "clojure.core",
   :line 2394,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([s]),
   :doc "Reads one object from the string s",
   :name "read-string"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L646",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/reduce",
   :namespace "clojure.core",
   :line 646,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([f coll] [f val coll]),
   :doc
   "f should be a function of 2 arguments. If val is not supplied,\nreturns the result of applying f to the first 2 items in coll, then\napplying f to that result and the 3rd item, etc. If coll contains no\nitems, f must accept no arguments as well, and reduce returns the\nresult of calling f with no arguments.  If coll has only 1 item, it\nis returned and f is not called.  If val is supplied, returns the\nresult of applying f to val and the first item in coll, then\napplying f to that result and the 2nd item, etc. If coll contains no\nitems, returns val and f is not called.",
   :name "reduce"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1411",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/ref",
   :namespace "clojure.core",
   :line 1411,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x] [x & options]),
   :doc
   "Creates and returns a Ref with an initial value of x and zero or\nmore options (in any order):\n\n:meta metadata-map\n\n:validator validate-fn\n\n:min-history (default 0)\n:max-history (default 10)\n\nIf metadata-map is supplied, it will be come the metadata on the\nref. validate-fn must be nil or a side-effect-free fn of one\nargument, which will be passed the intended new state on any state\nchange. If the new state is unacceptable, the validate-fn should\nreturn false or throw an exception. validate-fn will be called on\ntransaction commit, when all refs have their final values.\n\nNormally refs accumulate history dynamically as needed to deal with\nread demands. If you know in advance you will need history you can\nset :min-history to ensure it will be available when first needed (instead\nof after a read fault). History is limited, and the limit can be set\nwith :max-history.",
   :name "ref"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1550",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/ref-history-count",
   :namespace "clojure.core",
   :line 1550,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([ref]),
   :doc "Returns the history count of a ref",
   :name "ref-history-count"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1562",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/ref-max-history",
   :namespace "clojure.core",
   :line 1562,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([ref] [ref n]),
   :doc
   "Gets the max-history of a ref, or sets it and returns the ref",
   :name "ref-max-history"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1555",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/ref-min-history",
   :namespace "clojure.core",
   :line 1555,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([ref] [ref n]),
   :doc
   "Gets the min-history of a ref, or sets it and returns the ref",
   :name "ref-min-history"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1544",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/ref-set",
   :namespace "clojure.core",
   :line 1544,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([ref val]),
   :doc
   "Must be called in a transaction. Sets the value of ref.\nReturns val.",
   :name "ref-set"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2708",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/refer",
   :namespace "clojure.core",
   :line 2708,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([ns-sym & filters]),
   :doc
   "refers to all public vars of ns, subject to filters.\nfilters can include at most one each of:\n\n:exclude list-of-symbols\n:only list-of-symbols\n:rename map-of-fromsymbol-tosymbol\n\nFor each public interned var in the namespace named by the symbol,\nadds a mapping from the name of the var to the var to the current\nnamespace.  Throws an exception if name is already mapped to\nsomething else in the current namespace. Filters can be used to\nselect a subset, via inclusion or exclusion, or to provide a mapping\nto a symbol different from the var's name, in order to prevent\nclashes. Use :use in the ns macro in preference to calling this directly.",
   :name "refer"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3927",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/refer-clojure",
   :namespace "clojure.core",
   :line 3927,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([& filters]),
   :doc "Same as (refer 'clojure.core <filters>)",
   :name "refer-clojure"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1362",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/release-pending-sends",
   :namespace "clojure.core",
   :line 1362,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([]),
   :doc
   "Normally, actions sent directly or indirectly during another action\nare held until the action completes (changes the agent's\nstate). This function can be used to dispatch any pending sent\nactions immediately. This has no impact on actions sent during a\ntransaction, which are still held until commit. If no action is\noccurring, does nothing. Returns the number of actions dispatched.",
   :name "release-pending-sends"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L856",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/rem",
   :namespace "clojure.core",
   :line 856,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([num div]),
   :doc "remainder of dividing numerator by denominator.",
   :name "rem"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1809",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/remove",
   :namespace "clojure.core",
   :line 1809,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([pred coll]),
   :doc
   "Returns a lazy sequence of the items in coll for which\n(pred item) returns false. pred must be free of side-effects.",
   :name "remove"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1157",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/remove-method",
   :namespace "clojure.core",
   :line 1157,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([multifn dispatch-val]),
   :doc
   "Removes the method of multimethod associated with dispatch-value.",
   :name "remove-method"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2657",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/remove-ns",
   :namespace "clojure.core",
   :line 2657,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([sym]),
   :doc
   "Removes the namespace named by the symbol. Use with caution.\nCannot be used to remove the clojure namespace.",
   :name "remove-ns"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1388",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/remove-watch",
   :namespace "clojure.core",
   :line 1388,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([reference key]),
   :doc
   "Alpha - subject to change.\nRemoves a watch (set by add-watch) from a reference",
   :name "remove-watch"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1884",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/repeat",
   :namespace "clojure.core",
   :line 1884,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x] [n x]),
   :doc
   "Returns a lazy (infinite!, or length n if supplied) sequence of xs.",
   :name "repeat"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3467",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/repeatedly",
   :namespace "clojure.core",
   :line 3467,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([f]),
   :doc
   "Takes a function of no args, presumably with side effects, and returns an infinite\nlazy sequence of calls to it",
   :name "repeatedly"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3393",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/replace",
   :namespace "clojure.core",
   :line 3393,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([smap coll]),
   :doc
   "Given a map of replacement pairs and a vector/collection, returns a\nvector/seq with any elements = a key in smap replaced with the\ncorresponding val in smap",
   :name "replace"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1889",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/replicate",
   :namespace "clojure.core",
   :line 1889,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([n x]),
   :doc "Returns a lazy seq of n xs.",
   :name "replicate"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4079",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/require",
   :namespace "clojure.core",
   :line 4079,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([& args]),
   :doc
   "Loads libs, skipping any that are already loaded. Each argument is\neither a libspec that identifies a lib, a prefix list that identifies\nmultiple libs whose names share a common prefix, or a flag that modifies\nhow all the identified libs are loaded. Use :require in the ns macro\nin preference to calling this directly.\n\nLibs\n\nA 'lib' is a named set of resources in classpath whose contents define a\nlibrary of Clojure code. Lib names are symbols and each lib is associated\nwith a Clojure namespace and a Java package that share its name. A lib's\nname also locates its root directory within classpath using Java's\npackage name to classpath-relative path mapping. All resources in a lib\nshould be contained in the directory structure under its root directory.\nAll definitions a lib makes should be in its associated namespace.\n\n'require loads a lib by loading its root resource. The root resource path\nis derived from the lib name in the following manner:\nConsider a lib named by the symbol 'x.y.z; it has the root directory\n<classpath>/x/y/, and its root resource is <classpath>/x/y/z.clj. The root\nresource should contain code to create the lib's namespace (usually by using\nthe ns macro) and load any additional lib resources.\n\nLibspecs\n\nA libspec is a lib name or a vector containing a lib name followed by\noptions expressed as sequential keywords and arguments.\n\nRecognized options: :as\n:as takes a symbol as its argument and makes that symbol an alias to the\n  lib's namespace in the current namespace.\n\nPrefix Lists\n\nIt's common for Clojure code to depend on several libs whose names have\nthe same prefix. When specifying libs, prefix lists can be used to reduce\nrepetition. A prefix list contains the shared prefix followed by libspecs\nwith the shared prefix removed from the lib names. After removing the\nprefix, the names that remain must not contain any periods.\n\nFlags\n\nA flag is a keyword.\nRecognized flags: :reload, :reload-all, :verbose\n:reload forces loading of all the identified libs even if they are\n  already loaded\n:reload-all implies :reload and also forces loading of all libs that the\n  identified libs directly or indirectly load via require or use\n:verbose triggers printing information about each load, alias, and refer\n\nExample:\n\nThe following would load the libraries clojure.zip and clojure.set\nabbreviated as 's'.\n\n(require '(clojure zip [set :as s]))",
   :name "require"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1485",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/reset!",
   :namespace "clojure.core",
   :line 1485,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([atom newval]),
   :doc
   "Sets the value of atom to newval without regard for the\ncurrent value. Returns newval.",
   :name "reset!"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1511",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/reset-meta!",
   :namespace "clojure.core",
   :line 1511,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([iref metadata-map]),
   :doc
   "Atomically resets the metadata for a namespace/var/ref/agent/atom",
   :name "reset-meta!"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2827",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/resolve",
   :namespace "clojure.core",
   :line 2827,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([sym]),
   :doc "same as (ns-resolve *ns* symbol)",
   :name "resolve"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L54",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/rest",
   :namespace "clojure.core",
   :line 54,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc
   "Returns a possibly empty seq of the items after the first. Calls seq on its\nargument.",
   :name "rest"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3827",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/resultset-seq",
   :namespace "clojure.core",
   :line 3827,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([rs]),
   :doc
   "Creates and returns a lazy sequence of structmaps corresponding to\nthe rows in the java.sql.ResultSet rs",
   :name "resultset-seq"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L671",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/reverse",
   :namespace "clojure.core",
   :line 671,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc
   "Returns a seq of the items in coll in reverse order. Not lazy.",
   :name "reverse"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4255",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/reversible?",
   :namespace "clojure.core",
   :line 4255,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc "Returns true if coll implements Reversible",
   :name "reversible?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1043",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/rseq",
   :namespace "clojure.core",
   :line 1043,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([rev]),
   :doc
   "Returns, in constant time, a seq of the items in rev (which\ncan be a vector or sorted-map), in reverse order. If rev is empty returns nil",
   :name "rseq"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3452",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/rsubseq",
   :namespace "clojure.core",
   :line 3452,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists
   ([sc test key] [sc start-test start-key end-test end-key]),
   :doc
   "sc must be a sorted collection, test(s) one of <, <=, > or\n>=. Returns a reverse seq of those entries with keys ek for\nwhich (test (.. sc comparator (compare ek key)) 0) is true",
   :name "rsubseq"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L73",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/second",
   :namespace "clojure.core",
   :line 73,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Same as (first (next x))",
   :name "second"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1012",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/select-keys",
   :namespace "clojure.core",
   :line 1012,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([map keyseq]),
   :doc
   "Returns a map containing only those entries in map whose key is in keys",
   :name "select-keys"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1344",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/send",
   :namespace "clojure.core",
   :line 1344,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([a f & args]),
   :doc
   "Dispatch an action to an agent. Returns the agent immediately.\nSubsequently, in a thread from a thread pool, the state of the agent\nwill be set to the value of:\n\n(apply action-fn state-of-agent args)",
   :name "send"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1353",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/send-off",
   :namespace "clojure.core",
   :line 1353,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([a f & args]),
   :doc
   "Dispatch a potentially blocking action to an agent. Returns the\nagent immediately. Subsequently, in a separate thread, the state of\nthe agent will be set to the value of:\n\n(apply action-fn state-of-agent args)",
   :name "send-off"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L98",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/seq",
   :namespace "clojure.core",
   :line 98,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc
   "Returns a seq on the collection. If the collection is\nempty, returns nil.  (seq nil) returns nil. seq also works on\nStrings, native Java arrays (of reference types) and any objects\nthat implement Iterable.",
   :name "seq"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L113",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/seq?",
   :namespace "clojure.core",
   :line 113,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Return true if x implements ISeq",
   :name "seq?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3624",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/seque",
   :namespace "clojure.core",
   :line 3624,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([s] [n-or-q s]),
   :doc
   "Creates a queued seq on another (presumably lazy) seq s. The queued\nseq will produce a concrete seq in the background, and can get up to\nn items ahead of the consumer. n-or-q can be an integer n buffer\nsize, or an instance of java.util.concurrent BlockingQueue. Note\nthat reading from a seque can block if the reader gets ahead of the\nproducer.",
   :name "seque"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1689",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/sequence",
   :namespace "clojure.core",
   :line 1689,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc
   "Coerces coll to a (possibly empty) sequence, if it is not already\none. Will not force a lazy seq. (sequence nil) yields ()",
   :name "sequence"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4243",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/sequential?",
   :namespace "clojure.core",
   :line 4243,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc "Returns true if coll implements Sequential",
   :name "sequential?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2634",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/set",
   :namespace "clojure.core",
   :line 2634,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc "Returns a set of the distinct elements of coll.",
   :name "set"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1490",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/set-validator!",
   :namespace "clojure.core",
   :line 1490,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([iref validator-fn]),
   :doc
   "Sets the validator-fn for a var/ref/agent/atom. validator-fn must be nil or a\nside-effect-free fn of one argument, which will be passed the intended\nnew state on any state change. If the new state is unacceptable, the\nvalidator-fn should return false or throw an exception. If the current state (root\nvalue if var) is not acceptable to the new validator, an exception\nwill be thrown and the validator will not be changed.",
   :name "set-validator!"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4225",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/set?",
   :namespace "clojure.core",
   :line 4225,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Returns true if x implements IPersistentSet",
   :name "set?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2228",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/short",
   :namespace "clojure.core",
   :line 2228,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Coerce to short",
   :name "short"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3562",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/short-array",
   :namespace "clojure.core",
   :line 3562,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([size-or-seq] [size init-val-or-seq]),
   :doc "Creates an array of shorts",
   :name "short-array"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3602",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/shorts",
   :namespace "clojure.core",
   :line 3602,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([xs]),
   :doc "Casts to shorts[]",
   :name "shorts"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1405",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/shutdown-agents",
   :namespace "clojure.core",
   :line 1405,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([]),
   :doc
   "Initiates a shutdown of the thread pools that back the agent\nsystem. Running actions will complete, but no new actions will be\naccepted",
   :name "shutdown-agents"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3342",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/slurp",
   :namespace "clojure.core",
   :line 3342,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([f] [f enc]),
   :doc
   "Reads the file named by f using the encoding enc into a string\nand returns it.",
   :name "slurp"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1715",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/some",
   :namespace "clojure.core",
   :line 1715,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([pred coll]),
   :doc
   "Returns the first logical true value of (pred x) for any x in coll,\nelse nil.  One common idiom is to use a set as pred, for example\nthis will return :fred if :fred is in the sequence, otherwise nil:\n(some #{:fred} coll)",
   :name "some"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1968",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/sort",
   :namespace "clojure.core",
   :line 1968,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll] [comp coll]),
   :doc
   "Returns a sorted sequence of the items in coll. If no comparator is\nsupplied, uses compare. comparator must\nimplement java.util.Comparator.",
   :name "sort"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1981",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/sort-by",
   :namespace "clojure.core",
   :line 1981,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([keyfn coll] [keyfn comp coll]),
   :doc
   "Returns a sorted sequence of the items in coll, where the sort\norder is determined by comparing (keyfn item).  If no comparator is\nsupplied, uses compare. comparator must\nimplement java.util.Comparator.",
   :name "sort-by"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L287",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/sorted-map",
   :namespace "clojure.core",
   :line 287,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([& keyvals]),
   :doc
   "keyval => key val\nReturns a new sorted map with supplied mappings.",
   :name "sorted-map"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L293",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/sorted-map-by",
   :namespace "clojure.core",
   :line 293,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([comparator & keyvals]),
   :doc
   "keyval => key val\nReturns a new sorted map with supplied mappings, using the supplied comparator.",
   :name "sorted-map-by"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L299",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/sorted-set",
   :namespace "clojure.core",
   :line 299,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([& keys]),
   :doc "Returns a new sorted set with supplied keys.",
   :name "sorted-set"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L304",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/sorted-set-by",
   :namespace "clojure.core",
   :line 304,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([comparator & keys]),
   :doc
   "Returns a new sorted set with supplied keys, using the supplied comparator.",
   :name "sorted-set-by"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4247",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/sorted?",
   :namespace "clojure.core",
   :line 4247,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc "Returns true if coll implements Sorted",
   :name "sorted?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3261",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/special-form-anchor",
   :namespace "clojure.core",
   :line 3261,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc
   "Returns the anchor tag on http://clojure.org/special_forms for the\nspecial form x, or nil",
   :name "special-form-anchor"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3333",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/special-symbol?",
   :namespace "clojure.core",
   :line 3333,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([s]),
   :doc "Returns true if s names a special form",
   :name "special-symbol?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1874",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/split-at",
   :namespace "clojure.core",
   :line 1874,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([n coll]),
   :doc "Returns a vector of [(take n coll) (drop n coll)]",
   :name "split-at"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1879",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/split-with",
   :namespace "clojure.core",
   :line 1879,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([pred coll]),
   :doc
   "Returns a vector of [(take-while pred coll) (drop-while pred coll)]",
   :name "split-with"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L356",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/str",
   :namespace "clojure.core",
   :line 356,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([] [x] [x & ys]),
   :doc
   "With no args, returns the empty string. With one arg x, returns\nx.toString().  (str nil) returns the empty string. With more than\none arg, returns the concatenation of the str values of the args.",
   :name "str"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1684",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/stream?",
   :namespace "clojure.core",
   :line 1684,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Returns true if x is an instance of Stream",
   :name "stream?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L123",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/string?",
   :namespace "clojure.core",
   :line 123,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Return true if x is a String",
   :name "string?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2605",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/struct",
   :namespace "clojure.core",
   :line 2605,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([s & vals]),
   :doc
   "Returns a new structmap instance with the keys of the\nstructure-basis. vals must be supplied for basis keys in order -\nwhere values are not supplied they will default to nil.",
   :name "struct"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2597",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/struct-map",
   :namespace "clojure.core",
   :line 2597,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([s & inits]),
   :doc
   "Returns a new structmap instance with the keys of the\nstructure-basis. keyvals may contain all, some or none of the basis\nkeys - where values are not supplied they will default to nil.\nkeyvals can also contain keys not in the basis.",
   :name "struct-map"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3358",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/subs",
   :namespace "clojure.core",
   :line 3358,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([s start] [s start end]),
   :doc
   "Returns the substring of s beginning at start inclusive, and ending\nat end (defaults to length of string), exclusive.",
   :name "subs"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3437",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/subseq",
   :namespace "clojure.core",
   :line 3437,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists
   ([sc test key] [sc start-test start-key end-test end-key]),
   :doc
   "sc must be a sorted collection, test(s) one of <, <=, > or\n>=. Returns a seq of those entries with keys ek for\nwhich (test (.. sc comparator (compare ek key)) 0) is true",
   :name "subseq"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2398",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/subvec",
   :namespace "clojure.core",
   :line 2398,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([v start] [v start end]),
   :doc
   "Returns a persistent vector of the items in vector from\nstart (inclusive) to end (exclusive).  If end is not supplied,\ndefaults to (count vector). This operation is O(1) and very fast, as\nthe resulting vector shares structure with the original and no\ntrimming is done.",
   :name "subvec"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3688",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/supers",
   :namespace "clojure.core",
   :line 3688,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([class]),
   :doc
   "Returns the immediate and indirect superclasses and interfaces of c, if any",
   :name "supers"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1469",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/swap!",
   :namespace "clojure.core",
   :line 1469,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([atom f] [atom f x] [atom f x y] [atom f x y & args]),
   :doc
   "Atomically swaps the value of atom to be:\n(apply f current-value-of-atom args). Note that f may be called\nmultiple times, and thus should be free of side effects.  Returns\nthe value that was swapped in.",
   :name "swap!"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L380",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/symbol",
   :namespace "clojure.core",
   :line 380,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([name] [ns name]),
   :doc "Returns a Symbol with the given namespace and name.",
   :name "symbol"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L372",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/symbol?",
   :namespace "clojure.core",
   :line 372,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Return true if x is a Symbol",
   :name "symbol?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1577",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/sync",
   :namespace "clojure.core",
   :line 1577,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([flags-ignored-for-now & body]),
   :doc
   "transaction-flags => TBD, pass nil for now\n\nRuns the exprs (in an implicit do) in a transaction that encompasses\nexprs and any nested calls.  Starts a transaction if none is already\nrunning on this thread. Any uncaught exception will abort the\ntransaction and flow out of sync. The exprs may be run more than\nonce, but any effects on Refs will be atomic.",
   :name "sync"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3268",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/syntax-symbol-anchor",
   :namespace "clojure.core",
   :line 3268,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc
   "Returns the anchor tag on http://clojure.org/special_forms for the\nspecial form that uses syntax symbol x, or nil",
   :name "syntax-symbol-anchor"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1815",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/take",
   :namespace "clojure.core",
   :line 1815,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([n coll]),
   :doc
   "Returns a lazy sequence of the first n items in coll, or all items if\nthere are fewer than n.",
   :name "take"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1848",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/take-last",
   :namespace "clojure.core",
   :line 1848,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([n coll]),
   :doc
   "Returns a seq of the last n items in coll.  Depending on the type\nof coll may be no better than linear time.  For vectors, see also subvec.",
   :name "take-last"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2771",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/take-nth",
   :namespace "clojure.core",
   :line 2771,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([n coll]),
   :doc "Returns a lazy seq of every nth item in coll.",
   :name "take-nth"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1824",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/take-while",
   :namespace "clojure.core",
   :line 1824,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([pred coll]),
   :doc
   "Returns a lazy sequence of successive items from coll while\n(pred item) returns true. pred must be free of side-effects.",
   :name "take-while"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3157",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/test",
   :namespace "clojure.core",
   :line 3157,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([v]),
   :doc
   "test [v] finds fn at key :test in var metadata and calls it,\npresuming failure will throw exception",
   :name "test"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2666",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/the-ns",
   :namespace "clojure.core",
   :line 2666,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc
   "If passed a namespace, returns it. Else, when passed a symbol,\nreturns the namespace named by it, throwing an exception if not\nfound.",
   :name "the-ns"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2454",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/time",
   :namespace "clojure.core",
   :line 2454,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([expr]),
   :doc
   "Evaluates expr and prints the time it took.  Returns the value of\nexpr.",
   :name "time"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L255",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/to-array",
   :namespace "clojure.core",
   :line 255,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc
   "Returns an array of Objects containing the contents of coll, which\ncan be any Collection.  Maps to java.util.Collection.toArray().",
   :name "to-array"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2558",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/to-array-2d",
   :namespace "clojure.core",
   :line 2558,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc
   "Returns a (potentially-ragged) 2-dimensional array of Objects\ncontaining the contents of coll, which can be any Collection of any\nCollection.",
   :name "to-array-2d"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4279",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/trampoline",
   :namespace "clojure.core",
   :line 4279,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([f] [f & args]),
   :doc
   "trampoline can be used to convert algorithms requiring mutual\nrecursion without stack consumption. Calls f with supplied args, if\nany. If f returns a fn, calls that fn with no arguments, and\ncontinues to repeat, until the return value is not a fn, then\nreturns that non-fn value. Note that if you want to return a fn as a\nfinal value, you must wrap it in some data structure and unpack it\nafter trampoline returns.",
   :name "trampoline"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4603",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/transient",
   :namespace "clojure.core",
   :line 4603,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc
   "Alpha - subject to change.\nReturns a new, transient version of the collection, in constant time.",
   :name "transient"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3302",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/tree-seq",
   :namespace "clojure.core",
   :line 3302,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([branch? children root]),
   :doc
   "Returns a lazy sequence of the nodes in a tree, via a depth-first walk.\n branch? must be a fn of one arg that returns true if passed a node\n that can have children (but may not).  children must be a fn of one\n arg that returns a sequence of the children. Will only be called on\n nodes for which branch? returns true. Root is the root node of the\ntree.",
   :name "tree-seq"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L346",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/true?",
   :namespace "clojure.core",
   :line 346,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Returns true if x is the value true, false otherwise.",
   :name "true?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2199",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/type",
   :namespace "clojure.core",
   :line 2199,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Returns the :type metadata of x, or its Class if none",
   :name "type"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L809",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/unchecked-add",
   :namespace "clojure.core",
   :line 809,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x y]),
   :doc
   "Returns the sum of x and y, both int or long.\nNote - uses a primitive operator subject to overflow.",
   :name "unchecked-add"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L797",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/unchecked-dec",
   :namespace "clojure.core",
   :line 797,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc
   "Returns a number one less than x, an int or long.\nNote - uses a primitive operator subject to overflow.",
   :name "unchecked-dec"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L827",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/unchecked-divide",
   :namespace "clojure.core",
   :line 827,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x y]),
   :doc
   "Returns the division of x by y, both int or long.\nNote - uses a primitive operator subject to truncation.",
   :name "unchecked-divide"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L791",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/unchecked-inc",
   :namespace "clojure.core",
   :line 791,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc
   "Returns a number one greater than x, an int or long.\nNote - uses a primitive operator subject to overflow.",
   :name "unchecked-inc"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L821",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/unchecked-multiply",
   :namespace "clojure.core",
   :line 821,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x y]),
   :doc
   "Returns the product of x and y, both int or long.\nNote - uses a primitive operator subject to overflow.",
   :name "unchecked-multiply"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L803",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/unchecked-negate",
   :namespace "clojure.core",
   :line 803,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc
   "Returns the negation of x, an int or long.\nNote - uses a primitive operator subject to overflow.",
   :name "unchecked-negate"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L833",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/unchecked-remainder",
   :namespace "clojure.core",
   :line 833,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x y]),
   :doc
   "Returns the remainder of division of x by y, both int or long.\nNote - uses a primitive operator subject to truncation.",
   :name "unchecked-remainder"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L815",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/unchecked-subtract",
   :namespace "clojure.core",
   :line 815,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x y]),
   :doc
   "Returns the difference of x and y, both int or long.\nNote - uses a primitive operator subject to overflow.",
   :name "unchecked-subtract"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3790",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/underive",
   :namespace "clojure.core",
   :line 3790,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([tag parent] [h tag parent]),
   :doc
   "Removes a parent/child relationship between parent and\ntag. h must be a hierarchy obtained from make-hierarchy, if not\nsupplied defaults to, and modifies, the global hierarchy.",
   :name "underive"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4200",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/update-in",
   :namespace "clojure.core",
   :line 4200,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([m [k & ks] f & args]),
   :doc
   "'Updates' a value in a nested associative structure, where ks is a\nsequence of keys and f is a function that will take the old value\nand any supplied args and return the new value, and returns a new\nnested structure.  If any levels do not exist, hash-maps will be\ncreated.",
   :name "update-in"}
  {:source-url
   "http://github.com/clojure/clojure/blob/040f083efc16dd830a4508a35a04465e3e5677d3/src/clj/clojure/core_proxy.clj#L278",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/update-proxy",
   :namespace "clojure.core",
   :line 278,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/core_proxy.clj",
   :var-type "function",
   :arglists ([proxy mappings]),
   :doc
   "Takes a proxy instance and a map of strings (which must\ncorrespond to methods of the proxy superclass/superinterfaces) to\nfns (which must take arguments matching the corresponding method,\nplus an additional (explicit) first arg corresponding to this, and\nupdates (via assoc) the proxy's fn map. nil can be passed instead of\na fn, in which case the corresponding method will revert to the\ndefault behavior. Note that this function can be used to update the\nbehavior of an existing instance without changing its identity.",
   :name "update-proxy"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4140",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/use",
   :namespace "clojure.core",
   :line 4140,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([& args]),
   :doc
   "Like 'require, but also refers to each lib's namespace using\nclojure.core/refer. Use :use in the ns macro in preference to calling\nthis directly.\n\n'use accepts additional options in libspecs: :exclude, :only, :rename.\nThe arguments and semantics for :exclude, :only, and :rename are the same\nas those documented for clojure.core/refer.",
   :name "use"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1038",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/val",
   :namespace "clojure.core",
   :line 1038,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([e]),
   :doc "Returns the value in the map entry.",
   :name "val"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1029",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/vals",
   :namespace "clojure.core",
   :line 1029,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([map]),
   :doc "Returns a sequence of the map's values.",
   :name "vals"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2792",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/var-get",
   :namespace "clojure.core",
   :line 2792,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Gets the value in the var object",
   :name "var-get"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2796",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/var-set",
   :namespace "clojure.core",
   :line 2796,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x val]),
   :doc
   "Sets the value in the var object to val. The var must be\nthread-locally bound.",
   :name "var-set"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3338",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/var?",
   :namespace "clojure.core",
   :line 3338,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([v]),
   :doc "Returns true if v is of type clojure.lang.Var",
   :name "var?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L446",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/vary-meta",
   :namespace "clojure.core",
   :line 446,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([obj f & args]),
   :doc
   "Returns an object of the same type and value as obj, with\n(apply f (meta obj) args) as its metadata.",
   :name "vary-meta"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L267",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/vec",
   :namespace "clojure.core",
   :line 267,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([coll]),
   :doc "Creates a new vector containing the contents of coll.",
   :name "vec"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L261",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/vector",
   :namespace "clojure.core",
   :line 261,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([] [& args]),
   :doc "Creates a new vector containing the args.",
   :name "vector"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L133",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/vector?",
   :namespace "clojure.core",
   :line 133,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Return true if x implements IPersistentVector ",
   :name "vector?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L326",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/when",
   :namespace "clojure.core",
   :line 326,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([test & body]),
   :doc
   "Evaluates test. If logical true, evaluates body in an implicit do.",
   :name "when"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2991",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/when-first",
   :namespace "clojure.core",
   :line 2991,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([bindings & body]),
   :doc
   "bindings => x xs\n\nSame as (when (seq xs) (let [x (first xs)] body))",
   :name "when-first"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1209",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/when-let",
   :namespace "clojure.core",
   :line 1209,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([bindings & body]),
   :doc
   "bindings => binding-form test\n\nWhen test is true, evaluates body with binding-form bound to the value of test",
   :name "when-let"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L331",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/when-not",
   :namespace "clojure.core",
   :line 331,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([test & body]),
   :doc
   "Evaluates test. If logical false, evaluates body in an implicit do.",
   :name "when-not"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L4309",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/while",
   :namespace "clojure.core",
   :line 4309,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([test & body]),
   :doc
   "Repeatedly executes body while test expression is true. Presumes\nsome side-effect will cause test to become false/nil. Returns nil",
   :name "while"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1287",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/with-bindings",
   :namespace "clojure.core",
   :line 1287,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([binding-map & body]),
   :doc
   "Takes a map of Var/value pairs. Installs for the given Vars the associated\nvalues as thread-local bindings. The executes body. Pops the installed\nbindings after body was evaluated. Returns the value of body.",
   :name "with-bindings"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1276",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/with-bindings*",
   :namespace "clojure.core",
   :line 1276,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([binding-map f & args]),
   :doc
   "Takes a map of Var/value pairs. Installs for the given Vars the associated\nvalues as thread-local bindings. Then calls f with the supplied arguments.\nPops the installed bindings after f returned. Returns whatever f returns.",
   :name "with-bindings*"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3113",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/with-in-str",
   :namespace "clojure.core",
   :line 3113,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([s & body]),
   :doc
   "Evaluates body in a context in which *in* is bound to a fresh\nStringReader initialized with the string s.",
   :name "with-in-str"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2801",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/with-local-vars",
   :namespace "clojure.core",
   :line 2801,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([name-vals-vec & body]),
   :doc
   "varbinding=> symbol init-expr\n\nExecutes the exprs in a context in which the symbols are bound to\nvars with per-thread bindings to the init-exprs.  The symbols refer\nto the var objects themselves, and must be accessed with var-get and\nvar-set",
   :name "with-local-vars"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L161",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/with-meta",
   :namespace "clojure.core",
   :line 161,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([obj m]),
   :doc
   "Returns an object of the same type and value as obj, with\nmap m as its metadata.",
   :name "with-meta"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L2409",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/with-open",
   :namespace "clojure.core",
   :line 2409,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([bindings & body]),
   :doc
   "bindings => [name init ...]\n\nEvaluates body in a try expression with names bound to the values\nof the inits, and a finally clause that calls (.close name) on each\nname in reverse order.",
   :name "with-open"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3103",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/with-out-str",
   :namespace "clojure.core",
   :line 3103,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([& body]),
   :doc
   "Evaluates exprs in a context in which *out* is bound to a fresh\nStringWriter.  Returns the string created by any nested printing\ncalls.",
   :name "with-out-str"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3415",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/with-precision",
   :namespace "clojure.core",
   :line 3415,
   :file "clojure/core.clj",
   :var-type "macro",
   :arglists ([precision & exprs]),
   :doc
   "Sets the precision and rounding mode to be used for BigDecimal operations.\n\nUsage: (with-precision 10 (/ 1M 3))\nor:    (with-precision 10 :rounding HALF_DOWN (/ 1M 3))\n\nThe rounding mode is one of CEILING, FLOOR, HALF_UP, HALF_DOWN,\nHALF_EVEN, UP, DOWN and UNNECESSARY; it defaults to HALF_UP.",
   :name "with-precision"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L3325",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/xml-seq",
   :namespace "clojure.core",
   :line 3325,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([root]),
   :doc "A tree seq on the xml elements as per xml/parse",
   :name "xml-seq"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L600",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/zero?",
   :namespace "clojure.core",
   :line 600,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "Returns true if num is zero, else false",
   :name "zero?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/f4c58e3500b3668a0941ca21f9aa4f444de2c652/src/clj/clojure/core.clj#L1942",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.core-api.html#clojure.core/zipmap",
   :namespace "clojure.core",
   :line 1942,
   :file "clojure/core.clj",
   :var-type "function",
   :arglists ([keys vals]),
   :doc
   "Returns a map with the keys mapped to the corresponding vals.",
   :name "zipmap"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/inspector.clj#L148",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.inspector-api.html#clojure.inspector/inspect",
   :namespace "clojure.inspector",
   :line 148,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/inspector.clj",
   :var-type "function",
   :arglists ([x]),
   :doc "creates a graphical (Swing) inspector on the supplied object",
   :name "inspect"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/inspector.clj#L95",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.inspector-api.html#clojure.inspector/inspect-table",
   :namespace "clojure.inspector",
   :line 95,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/inspector.clj",
   :var-type "function",
   :arglists ([data]),
   :doc
   "creates a graphical (Swing) inspector on the supplied regular\ndata, which must be a sequential data structure of data structures\nof equal length",
   :name "inspect-table"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/inspector.clj#L87",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.inspector-api.html#clojure.inspector/inspect-tree",
   :namespace "clojure.inspector",
   :line 87,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/inspector.clj",
   :var-type "function",
   :arglists ([data]),
   :doc
   "creates a graphical (Swing) inspector on the supplied hierarchical data",
   :name "inspect-tree"}
  {:source-url
   "http://github.com/clojure/clojure/blob/2cc710e7aeaab08e0739debe21e2cc6b7020e1b1/src/clj/clojure/main.clj#L206",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.main-api.html#clojure.main/load-script",
   :namespace "clojure.main",
   :line 206,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/main.clj",
   :var-type "function",
   :arglists ([path]),
   :doc
   "Loads Clojure source from a file or resource given its path. Paths\nbeginning with @ or @/ are considered relative to classpath.",
   :name "load-script"}
  {:source-url
   "http://github.com/clojure/clojure/blob/2cc710e7aeaab08e0739debe21e2cc6b7020e1b1/src/clj/clojure/main.clj#L310",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.main-api.html#clojure.main/main",
   :namespace "clojure.main",
   :line 310,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/main.clj",
   :var-type "function",
   :arglists ([& args]),
   :doc
   "Usage: java -cp clojure.jar clojure.main [init-opt*] [main-opt] [arg*]\n\nWith no options or args, runs an interactive Read-Eval-Print Loop\n\ninit options:\n  -i, --init path   Load a file or resource\n  -e, --eval string Evaluate expressions in string; print non-nil values\n\nmain options:\n  -r, --repl        Run a repl\n  path              Run a script from from a file or resource\n  -                 Run a script from standard input\n  -h, -?, --help    Print this help message and exit\n\noperation:\n\n  - Establishes thread-local bindings for commonly set!-able vars\n  - Enters the user namespace\n  - Binds *command-line-args* to a seq of strings containing command line\n    args that appear after any main option\n  - Runs all init options in order\n  - Runs a repl or script if requested\n\nThe init options may be repeated and mixed freely, but must appear before\nany main option. The appearance of any eval option before running a repl\nsuppresses the usual repl greeting message: \"Clojure ~(clojure-version)\".\n\nPaths may be absolute or relative in the filesystem or relative to\nclasspath. Classpath-relative paths have prefix of @ or @/",
   :name "main"}
  {:source-url
   "http://github.com/clojure/clojure/blob/2cc710e7aeaab08e0739debe21e2cc6b7020e1b1/src/clj/clojure/main.clj#L118",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.main-api.html#clojure.main/repl",
   :namespace "clojure.main",
   :line 118,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/main.clj",
   :var-type "function",
   :arglists ([& options]),
   :doc
   "Generic, reusable, read-eval-print loop. By default, reads from *in*,\nwrites to *out*, and prints exception summaries to *err*. If you use the\ndefault :read hook, *in* must either be an instance of\nLineNumberingPushbackReader or duplicate its behavior of both supporting\n.unread and collapsing CR, LF, and CRLF into a single \\newline. Options\nare sequential keyword-value pairs. Available options and their defaults:\n\n   - :init, function of no arguments, initialization hook called with\n     bindings for set!-able vars in place.\n     default: #()\n\n   - :need-prompt, function of no arguments, called before each\n     read-eval-print except the first, the user will be prompted if it\n     returns true.\n     default: (if (instance? LineNumberingPushbackReader *in*)\n                #(.atLineStart *in*)\n                #(identity true))\n\n   - :prompt, function of no arguments, prompts for more input.\n     default: repl-prompt\n\n   - :flush, function of no arguments, flushes output\n     default: flush\n\n   - :read, function of two arguments, reads from *in*:\n       - returns its first argument to request a fresh prompt\n         - depending on need-prompt, this may cause the repl to prompt\n           before reading again\n       - returns its second argument to request an exit from the repl\n       - else returns the next object read from the input stream\n     default: repl-read\n\n   - :eval, funtion of one argument, returns the evaluation of its\n     argument\n     default: eval\n\n   - :print, function of one argument, prints its argument to the output\n     default: prn\n\n   - :caught, function of one argument, a throwable, called when\n     read, eval, or print throws an exception or error\n     default: repl-caught",
   :name "repl"}
  {:source-url
   "http://github.com/clojure/clojure/blob/2cc710e7aeaab08e0739debe21e2cc6b7020e1b1/src/clj/clojure/main.clj#L113",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.main-api.html#clojure.main/repl-caught",
   :namespace "clojure.main",
   :line 113,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/main.clj",
   :var-type "function",
   :arglists ([e]),
   :doc "Default :caught hook for repl",
   :name "repl-caught"}
  {:source-url
   "http://github.com/clojure/clojure/blob/2cc710e7aeaab08e0739debe21e2cc6b7020e1b1/src/clj/clojure/main.clj#L105",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.main-api.html#clojure.main/repl-exception",
   :namespace "clojure.main",
   :line 105,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/main.clj",
   :var-type "function",
   :arglists ([throwable]),
   :doc
   "Returns CompilerExceptions in tact, but only the root cause of other\nthrowables",
   :name "repl-exception"}
  {:source-url
   "http://github.com/clojure/clojure/blob/2cc710e7aeaab08e0739debe21e2cc6b7020e1b1/src/clj/clojure/main.clj#L41",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.main-api.html#clojure.main/repl-prompt",
   :namespace "clojure.main",
   :line 41,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/main.clj",
   :var-type "function",
   :arglists ([]),
   :doc "Default :prompt hook for repl",
   :name "repl-prompt"}
  {:source-url
   "http://github.com/clojure/clojure/blob/2cc710e7aeaab08e0739debe21e2cc6b7020e1b1/src/clj/clojure/main.clj#L78",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.main-api.html#clojure.main/repl-read",
   :namespace "clojure.main",
   :line 78,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/main.clj",
   :var-type "function",
   :arglists ([request-prompt request-exit]),
   :doc
   "Default :read hook for repl. Reads from *in* which must either be an\ninstance of LineNumberingPushbackReader or duplicate its behavior of both\nsupporting .unread and collapsing all of CR, LF, and CRLF into a single\n\\newline. repl-read:\n  - skips whitespace, then\n    - returns request-prompt on start of line, or\n    - returns request-exit on end of stream, or\n    - reads an object from the input stream, then\n      - skips the next input character if it's end of line, then\n      - returns the object.",
   :name "repl-read"}
  {:source-url
   "http://github.com/clojure/clojure/blob/2cc710e7aeaab08e0739debe21e2cc6b7020e1b1/src/clj/clojure/main.clj#L46",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.main-api.html#clojure.main/skip-if-eol",
   :namespace "clojure.main",
   :line 46,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/main.clj",
   :var-type "function",
   :arglists ([s]),
   :doc
   "If the next character on stream s is a newline, skips it, otherwise\nleaves the stream untouched. Returns :line-start, :stream-end, or :body\nto indicate the relative location of the next character on s. The stream\nmust either be an instance of LineNumberingPushbackReader or duplicate\nits behavior of both supporting .unread and collapsing all of CR, LF, and\nCRLF to a single \\newline.",
   :name "skip-if-eol"}
  {:source-url
   "http://github.com/clojure/clojure/blob/2cc710e7aeaab08e0739debe21e2cc6b7020e1b1/src/clj/clojure/main.clj#L60",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.main-api.html#clojure.main/skip-whitespace",
   :namespace "clojure.main",
   :line 60,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/main.clj",
   :var-type "function",
   :arglists ([s]),
   :doc
   "Skips whitespace characters on stream s. Returns :line-start, :stream-end,\nor :body to indicate the relative location of the next character on s.\nInterprets comma as whitespace and semicolon as comment to end of line.\nDoes not interpret #! as comment to end of line because only one\ncharacter of lookahead is available. The stream must either be an\ninstance of LineNumberingPushbackReader or duplicate its behavior of both\nsupporting .unread and collapsing all of CR, LF, and CRLF to a single\n\\newline.",
   :name "skip-whitespace"}
  {:source-url
   "http://github.com/clojure/clojure/blob/2cc710e7aeaab08e0739debe21e2cc6b7020e1b1/src/clj/clojure/main.clj#L20",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.main-api.html#clojure.main/with-bindings",
   :namespace "clojure.main",
   :line 20,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/main.clj",
   :var-type "macro",
   :arglists ([& body]),
   :doc
   "Executes body in the context of thread-local bindings for several vars\nthat often need to be set!: *ns* *warn-on-reflection* *math-context*\n*print-meta* *print-length* *print-level* *compile-path*\n*command-line-args* *1 *2 *3 *e",
   :name "with-bindings"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/set.clj#L46",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.set-api.html#clojure.set/difference",
   :namespace "clojure.set",
   :line 46,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/set.clj",
   :var-type "function",
   :arglists ([s1] [s1 s2] [s1 s2 & sets]),
   :doc
   "Return a set that is the first set without elements of the remaining sets",
   :name "difference"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/set.clj#L87",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.set-api.html#clojure.set/index",
   :namespace "clojure.set",
   :line 87,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/set.clj",
   :var-type "function",
   :arglists ([xrel ks]),
   :doc
   "Returns a map of the distinct values of ks in the xrel mapped to a\nset of the maps in xrel with the corresponding values of ks.",
   :name "index"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/set.clj#L31",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.set-api.html#clojure.set/intersection",
   :namespace "clojure.set",
   :line 31,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/set.clj",
   :var-type "function",
   :arglists ([s1] [s1 s2] [s1 s2 & sets]),
   :doc "Return a set that is the intersection of the input sets",
   :name "intersection"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/set.clj#L101",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.set-api.html#clojure.set/join",
   :namespace "clojure.set",
   :line 101,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/set.clj",
   :var-type "function",
   :arglists ([xrel yrel] [xrel yrel km]),
   :doc
   "When passed 2 rels, returns the rel corresponding to the natural\njoin. When passed an additional keymap, joins on the corresponding\nkeys.",
   :name "join"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/set.clj#L97",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.set-api.html#clojure.set/map-invert",
   :namespace "clojure.set",
   :line 97,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/set.clj",
   :var-type "function",
   :arglists ([m]),
   :doc "Returns the map with the vals mapped to the keys.",
   :name "map-invert"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/set.clj#L67",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.set-api.html#clojure.set/project",
   :namespace "clojure.set",
   :line 67,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/set.clj",
   :var-type "function",
   :arglists ([xrel ks]),
   :doc
   "Returns a rel of the elements of xrel with only the keys in ks",
   :name "project"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/set.clj#L82",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.set-api.html#clojure.set/rename",
   :namespace "clojure.set",
   :line 82,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/set.clj",
   :var-type "function",
   :arglists ([xrel kmap]),
   :doc
   "Returns a rel of the maps in xrel with the keys in kmap renamed to the vals in kmap",
   :name "rename"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/set.clj#L72",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.set-api.html#clojure.set/rename-keys",
   :namespace "clojure.set",
   :line 72,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/set.clj",
   :var-type "function",
   :arglists ([map kmap]),
   :doc
   "Returns the map with the keys in kmap renamed to the vals in kmap",
   :name "rename-keys"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/set.clj#L61",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.set-api.html#clojure.set/select",
   :namespace "clojure.set",
   :line 61,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/set.clj",
   :var-type "function",
   :arglists ([pred xset]),
   :doc "Returns a set of the elements for which pred is true",
   :name "select"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/set.clj#L19",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.set-api.html#clojure.set/union",
   :namespace "clojure.set",
   :line 19,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/set.clj",
   :var-type "function",
   :arglists ([] [s1] [s1 s2] [s1 s2 & sets]),
   :doc "Return a set that is the union of the input sets",
   :name "union"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/stacktrace.clj#L69",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.stacktrace-api.html#clojure.stacktrace/e",
   :namespace "clojure.stacktrace",
   :line 69,
   :file "clojure/stacktrace.clj",
   :var-type "function",
   :arglists ([]),
   :doc
   "REPL utility.  Prints a brief stack trace for the root cause of the\nmost recent exception.",
   :name "e"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/stacktrace.clj#L60",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.stacktrace-api.html#clojure.stacktrace/print-cause-trace",
   :namespace "clojure.stacktrace",
   :line 60,
   :file "clojure/stacktrace.clj",
   :var-type "function",
   :arglists ([tr] [tr n]),
   :doc
   "Like print-stack-trace but prints chained exceptions (causes).",
   :name "print-cause-trace"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/stacktrace.clj#L41",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.stacktrace-api.html#clojure.stacktrace/print-stack-trace",
   :namespace "clojure.stacktrace",
   :line 41,
   :file "clojure/stacktrace.clj",
   :var-type "function",
   :arglists ([tr] [tr n]),
   :doc
   "Prints a Clojure-oriented stack trace of tr, a Throwable.\nPrints a maximum of n stack frames (default: unlimited).\nDoes not print chained exceptions (causes).",
   :name "print-stack-trace"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/stacktrace.clj#L36",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.stacktrace-api.html#clojure.stacktrace/print-throwable",
   :namespace "clojure.stacktrace",
   :line 36,
   :file "clojure/stacktrace.clj",
   :var-type "function",
   :arglists ([tr]),
   :doc "Prints the class and message of a Throwable.",
   :name "print-throwable"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/stacktrace.clj#L25",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.stacktrace-api.html#clojure.stacktrace/print-trace-element",
   :namespace "clojure.stacktrace",
   :line 25,
   :file "clojure/stacktrace.clj",
   :var-type "function",
   :arglists ([e]),
   :doc
   "Prints a Clojure-oriented view of one element in a stack trace.",
   :name "print-trace-element"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/stacktrace.clj#L18",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.stacktrace-api.html#clojure.stacktrace/root-cause",
   :namespace "clojure.stacktrace",
   :line 18,
   :file "clojure/stacktrace.clj",
   :var-type "function",
   :arglists ([tr]),
   :doc "Returns the last 'cause' Throwable in a chain of Throwables.",
   :name "root-cause"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/template.clj#L30",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.template-api.html#clojure.template/apply-template",
   :namespace "clojure.template",
   :line 30,
   :file "clojure/template.clj",
   :var-type "function",
   :arglists ([argv expr values]),
   :doc
   "For use in macros.  argv is an argument list, as in defn.  expr is\na quoted expression using the symbols in argv.  values is a sequence\nof values to be used for the arguments.\n\napply-template will recursively replace argument symbols in expr\nwith their corresponding values, returning a modified expr.\n\nExample: (apply-template '[x] '(+ x x) '[2])\n         ;=> (+ 2 2)",
   :name "apply-template"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/template.clj#L45",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.template-api.html#clojure.template/do-template",
   :namespace "clojure.template",
   :line 45,
   :file "clojure/template.clj",
   :var-type "macro",
   :arglists ([argv expr & values]),
   :doc
   "Repeatedly copies expr (in a do block) for each group of arguments\nin values.  values are automatically partitioned by the number of\narguments in argv, an argument vector as in defn.\n\nExample: (macroexpand '(do-template [x y] (+ y x) 2 4 3 5))\n         ;=> (do (+ 4 2) (+ 5 3))",
   :name "do-template"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L239",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/*load-tests*",
   :namespace "clojure.test",
   :line 239,
   :file "clojure/test.clj",
   :var-type "var",
   :doc
   "True by default.  If set to false, no test functions will\nbe created by deftest, set-test, or with-test.  Use this to omit\ntests when compiling or loading production code.",
   :name "*load-tests*"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L245",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/*stack-trace-depth*",
   :namespace "clojure.test",
   :line 245,
   :file "clojure/test.clj",
   :var-type "var",
   :doc
   "The maximum depth of stack traces to print when an Exception\nis thrown during a test.  Defaults to nil, which means print the \ncomplete stack trace.",
   :name "*stack-trace-depth*"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L518",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/are",
   :namespace "clojure.test",
   :line 518,
   :file "clojure/test.clj",
   :var-type "macro",
   :arglists ([argv expr & args]),
   :doc
   "Checks multiple assertions with a template expression.\nSee clojure.template/do-template for an explanation of\ntemplates.\n\nExample: (are [x y] (= x y)  \n              2 (+ 1 1)\n              4 (* 2 2))\nExpands to: \n         (do (is (= 2 (+ 1 1)))\n             (is (= 4 (* 2 2))))\n\nNote: This breaks some reporting features, such as line numbers.",
   :name "are"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L404",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/assert-any",
   :namespace "clojure.test",
   :line 404,
   :file "clojure/test.clj",
   :var-type "function",
   :arglists ([msg form]),
   :doc
   "Returns generic assertion code for any test, including macros, Java\nmethod calls, or isolated symbols.",
   :name "assert-any"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L386",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/assert-predicate",
   :namespace "clojure.test",
   :line 386,
   :file "clojure/test.clj",
   :var-type "function",
   :arglists ([msg form]),
   :doc
   "Returns generic assertion code for any functional predicate.  The\n'expected' argument to 'report' will contains the original form, the\n'actual' argument will contain the form with all its sub-forms\nevaluated.  If the predicate returns false, the 'actual' form will\nbe wrapped in (not...).",
   :name "assert-predicate"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L618",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/compose-fixtures",
   :namespace "clojure.test",
   :line 618,
   :file "clojure/test.clj",
   :var-type "function",
   :arglists ([f1 f2]),
   :doc
   "Composes two fixture functions, creating a new fixture function\nthat combines their behavior.",
   :name "compose-fixtures"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L557",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/deftest",
   :namespace "clojure.test",
   :line 557,
   :file "clojure/test.clj",
   :var-type "macro",
   :arglists ([name & body]),
   :doc
   "Defines a test function with no arguments.  Test functions may call\nother tests, so tests may be composed.  If you compose tests, you\nshould also define a function named test-ns-hook; run-tests will\ncall test-ns-hook instead of testing all vars.\n\nNote: Actually, the test body goes in the :test metadata on the var,\nand the real function (the value of the var) calls test-var on\nitself.\n\nWhen *load-tests* is false, deftest is ignored.",
   :name "deftest"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L573",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/deftest-",
   :namespace "clojure.test",
   :line 573,
   :file "clojure/test.clj",
   :var-type "macro",
   :arglists ([name & body]),
   :doc "Like deftest but creates a private var.",
   :name "deftest-"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L275",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/file-position",
   :namespace "clojure.test",
   :line 275,
   :file "clojure/test.clj",
   :var-type "function",
   :arglists ([n]),
   :doc
   "Returns a vector [filename line-number] for the nth call up the\nstack.",
   :name "file-position"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L375",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/function?",
   :namespace "clojure.test",
   :line 375,
   :file "clojure/test.clj",
   :var-type "function",
   :arglists ([x]),
   :doc
   "Returns true if argument is a function or a symbol that resolves to\na function (not a macro).",
   :name "function?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L368",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/get-possibly-unbound-var",
   :namespace "clojure.test",
   :line 368,
   :file "clojure/test.clj",
   :var-type "function",
   :arglists ([v]),
   :doc "Like var-get but returns nil if the var is unbound.",
   :name "get-possibly-unbound-var"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L300",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/inc-report-counter",
   :namespace "clojure.test",
   :line 300,
   :file "clojure/test.clj",
   :var-type "function",
   :arglists ([name]),
   :doc
   "Increments the named counter in *report-counters*, a ref to a map.\nDoes nothing if *report-counters* is nil.",
   :name "inc-report-counter"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L501",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/is",
   :namespace "clojure.test",
   :line 501,
   :file "clojure/test.clj",
   :var-type "macro",
   :arglists ([form] [form msg]),
   :doc
   "Generic assertion macro.  'form' is any predicate test.\n'msg' is an optional message to attach to the assertion.\n\nExample: (is (= 4 (+ 2 2)) \"Two plus two should be 4\")\n\nSpecial forms:\n\n(is (thrown? c body)) checks that an instance of c is thrown from\nbody, fails if not; then returns the thing thrown.\n\n(is (thrown-with-msg? c re body)) checks that an instance of c is\nthrown AND that the message on the exception matches (with\nre-matches) the regular expression re.",
   :name "is"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L624",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/join-fixtures",
   :namespace "clojure.test",
   :line 624,
   :file "clojure/test.clj",
   :var-type "function",
   :arglists ([fixtures]),
   :doc
   "Composes a collection of fixtures, in order.  Always returns a valid\nfixture function, even if the collection is empty.",
   :name "join-fixtures"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L312",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/report",
   :namespace "clojure.test",
   :line 312,
   :file "clojure/test.clj",
   :var-type "multimethod",
   :doc
   "Generic reporting function, may be overridden to plug in\ndifferent report formats (e.g., TAP, JUnit).  Assertions such as\n'is' call 'report' to indicate results.  The argument given to\n'report' will be a map with a :type key.  See the documentation at\nthe top of test_is.clj for more information on the types of\narguments for 'report'.",
   :name "report"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L695",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/run-all-tests",
   :namespace "clojure.test",
   :line 695,
   :file "clojure/test.clj",
   :var-type "function",
   :arglists ([] [re]),
   :doc
   "Runs all tests in all namespaces; prints results.\nOptional argument is a regular expression; only namespaces with\nnames matching the regular expression (with re-matches) will be\ntested.",
   :name "run-all-tests"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L684",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/run-tests",
   :namespace "clojure.test",
   :line 684,
   :file "clojure/test.clj",
   :var-type "function",
   :arglists ([] [& namespaces]),
   :doc
   "Runs all tests in the given namespaces; prints results.\nDefaults to current namespace if none given.  Returns a map\nsummarizing test results.",
   :name "run-tests"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L581",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/set-test",
   :namespace "clojure.test",
   :line 581,
   :file "clojure/test.clj",
   :var-type "macro",
   :arglists ([name & body]),
   :doc
   "Experimental.\nSets :test metadata of the named var to a fn with the given body.\nThe var must already exist.  Does not modify the value of the var.\n\nWhen *load-tests* is false, set-test is ignored.",
   :name "set-test"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L703",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/successful?",
   :namespace "clojure.test",
   :line 703,
   :file "clojure/test.clj",
   :var-type "function",
   :arglists ([summary]),
   :doc
   "Returns true if the given test summary indicates all tests\nwere successful, false otherwise.",
   :name "successful?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L649",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/test-all-vars",
   :namespace "clojure.test",
   :line 649,
   :file "clojure/test.clj",
   :var-type "function",
   :arglists ([ns]),
   :doc
   "Calls test-var on every var interned in the namespace, with fixtures.",
   :name "test-all-vars"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L660",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/test-ns",
   :namespace "clojure.test",
   :line 660,
   :file "clojure/test.clj",
   :var-type "function",
   :arglists ([ns]),
   :doc
   "If the namespace defines a function named test-ns-hook, calls that.\nOtherwise, calls test-all-vars on the namespace.  'ns' is a\nnamespace object or a symbol.\n\nInternally binds *report-counters* to a ref initialized to\n*inital-report-counters*.  Returns the final, dereferenced state of\n*report-counters*.",
   :name "test-ns"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L635",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/test-var",
   :namespace "clojure.test",
   :line 635,
   :file "clojure/test.clj",
   :var-type "function",
   :arglists ([v]),
   :doc
   "If v has a function in its :test metadata, calls that function,\nwith *testing-vars* bound to (conj *testing-vars* v).",
   :name "test-var"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L534",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/testing",
   :namespace "clojure.test",
   :line 534,
   :file "clojure/test.clj",
   :var-type "macro",
   :arglists ([string & body]),
   :doc
   "Adds a new string to the list of testing contexts.  May be nested,\nbut must occur inside a test function (deftest).",
   :name "testing"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L294",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/testing-contexts-str",
   :namespace "clojure.test",
   :line 294,
   :file "clojure/test.clj",
   :var-type "function",
   :arglists ([]),
   :doc
   "Returns a string representation of the current test context. Joins\nstrings in *testing-contexts* with spaces.",
   :name "testing-contexts-str"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L282",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/testing-vars-str",
   :namespace "clojure.test",
   :line 282,
   :file "clojure/test.clj",
   :var-type "function",
   :arglists ([]),
   :doc
   "Returns a string representation of the current test.  Renders names\nin *testing-vars* as a list, then the source file and line of\ncurrent assertion.",
   :name "testing-vars-str"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L486",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/try-expr",
   :namespace "clojure.test",
   :line 486,
   :file "clojure/test.clj",
   :var-type "macro",
   :arglists ([msg form]),
   :doc
   "Used by the 'is' macro to catch unexpected exceptions.\nYou don't call this.",
   :name "try-expr"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L601",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/use-fixtures",
   :namespace "clojure.test",
   :line 601,
   :file "clojure/test.clj",
   :var-type "multimethod",
   :doc
   "Wrap test runs in a fixture function to perform setup and\nteardown. Using a fixture-type of :each wraps every test\nindividually, while:once wraps the whole run in a single function.",
   :name "use-fixtures"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L545",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/with-test",
   :namespace "clojure.test",
   :line 545,
   :file "clojure/test.clj",
   :var-type "macro",
   :arglists ([definition & body]),
   :doc
   "Takes any definition form (that returns a Var) as the first argument.\nRemaining body goes in the :test metadata function for that Var.\n\nWhen *load-tests* is false, only evaluates the definition, ignoring\nthe tests.",
   :name "with-test"}
  {:source-url
   "http://github.com/clojure/clojure/blob/607389029cfec50f32b73c00a6f66d0a1dbcda23/src/clj/clojure/test.clj#L265",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test/with-test-out",
   :namespace "clojure.test",
   :line 265,
   :file "clojure/test.clj",
   :var-type "macro",
   :arglists ([& body]),
   :doc "Runs body with *out* bound to the value of *test-out*.",
   :name "with-test-out"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/walk.clj#L90",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.walk-api.html#clojure.walk/keywordize-keys",
   :namespace "clojure.walk",
   :line 90,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/walk.clj",
   :var-type "function",
   :arglists ([m]),
   :doc
   "Recursively transforms all map keys from strings to keywords.",
   :name "keywordize-keys"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/walk.clj#L118",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.walk-api.html#clojure.walk/macroexpand-all",
   :namespace "clojure.walk",
   :line 118,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/walk.clj",
   :var-type "function",
   :arglists ([form]),
   :doc "Recursively performs all possible macroexpansions in form.",
   :name "macroexpand-all"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/walk.clj#L52",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.walk-api.html#clojure.walk/postwalk",
   :namespace "clojure.walk",
   :line 52,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/walk.clj",
   :var-type "function",
   :arglists ([f form]),
   :doc
   "Performs a depth-first, post-order traversal of form.  Calls f on\neach sub-form, uses f's return value in place of the original.\nRecognizes all Clojure data structures except sorted-map-by.\nConsumes seqs as with doall.",
   :name "postwalk"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/walk.clj#L78",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.walk-api.html#clojure.walk/postwalk-demo",
   :namespace "clojure.walk",
   :line 78,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/walk.clj",
   :var-type "function",
   :arglists ([form]),
   :doc
   "Demonstrates the behavior of postwalk by printing each form as it is\nwalked.  Returns form.",
   :name "postwalk-demo"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/walk.clj#L111",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.walk-api.html#clojure.walk/postwalk-replace",
   :namespace "clojure.walk",
   :line 111,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/walk.clj",
   :var-type "function",
   :arglists ([smap form]),
   :doc
   "Recursively transforms form by replacing keys in smap with their\nvalues.  Like clojure/replace but works on any data structure.  Does\nreplacement at the leaves of the tree first.",
   :name "postwalk-replace"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/walk.clj#L60",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.walk-api.html#clojure.walk/prewalk",
   :namespace "clojure.walk",
   :line 60,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/walk.clj",
   :var-type "function",
   :arglists ([f form]),
   :doc "Like postwalk, but does pre-order traversal.",
   :name "prewalk"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/walk.clj#L84",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.walk-api.html#clojure.walk/prewalk-demo",
   :namespace "clojure.walk",
   :line 84,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/walk.clj",
   :var-type "function",
   :arglists ([form]),
   :doc
   "Demonstrates the behavior of prewalk by printing each form as it is\nwalked.  Returns form.",
   :name "prewalk-demo"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/walk.clj#L104",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.walk-api.html#clojure.walk/prewalk-replace",
   :namespace "clojure.walk",
   :line 104,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/walk.clj",
   :var-type "function",
   :arglists ([smap form]),
   :doc
   "Recursively transforms form by replacing keys in smap with their\nvalues.  Like clojure/replace but works on any data structure.  Does\nreplacement at the root of the tree first.",
   :name "prewalk-replace"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/walk.clj#L97",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.walk-api.html#clojure.walk/stringify-keys",
   :namespace "clojure.walk",
   :line 97,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/walk.clj",
   :var-type "function",
   :arglists ([m]),
   :doc
   "Recursively transforms all map keys from keywords to strings.",
   :name "stringify-keys"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/walk.clj#L35",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.walk-api.html#clojure.walk/walk",
   :namespace "clojure.walk",
   :line 35,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/walk.clj",
   :var-type "function",
   :arglists ([inner outer form]),
   :doc
   "Traverses form, an arbitrary data structure.  inner and outer are\nfunctions.  Applies inner to each element of form, building up a\ndata structure of the same type, then applies outer to the result.\nRecognizes all Clojure data structures except sorted-map-by.\nConsumes seqs as with doall.",
   :name "walk"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/xml.clj#L78",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.xml-api.html#clojure.xml/parse",
   :namespace "clojure.xml",
   :line 78,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/xml.clj",
   :var-type "function",
   :arglists ([s] [s startparse]),
   :doc
   "Parses and loads the source s, which can be a File, InputStream or\nString naming a URI. Returns a tree of the xml/element struct-map,\nwhich has the keys :tag, :attrs, and :content. and accessor fns tag,\nattrs, and content. Other parsers can be supplied by passing\nstartparse, a fn taking a source and a ContentHandler and returning\na parser",
   :name "parse"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L200",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/append-child",
   :namespace "clojure.zip",
   :line 200,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc item]),
   :doc
   "Inserts the item as the rightmost child of the node at this loc,\nwithout moving",
   :name "append-child"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L64",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/branch?",
   :namespace "clojure.zip",
   :line 64,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc]),
   :doc "Returns true if the node at loc is a branch",
   :name "branch?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L69",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/children",
   :namespace "clojure.zip",
   :line 69,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc]),
   :doc
   "Returns a seq of the children of node at loc, which must be a branch",
   :name "children"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L98",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/down",
   :namespace "clojure.zip",
   :line 98,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc]),
   :doc
   "Returns the loc of the leftmost child of the node at this loc, or\nnil if no children",
   :name "down"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L189",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/edit",
   :namespace "clojure.zip",
   :line 189,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc f & args]),
   :doc
   "Replaces the node at this loc with the value of (f node args)",
   :name "edit"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L232",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/end?",
   :namespace "clojure.zip",
   :line 232,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc]),
   :doc "Returns true if loc represents the end of a depth-first walk",
   :name "end?"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L194",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/insert-child",
   :namespace "clojure.zip",
   :line 194,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc item]),
   :doc
   "Inserts the item as the leftmost child of the node at this loc,\nwithout moving",
   :name "insert-child"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L165",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/insert-left",
   :namespace "clojure.zip",
   :line 165,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc item]),
   :doc
   "Inserts the item as the left sibling of the node at this loc,\nwithout moving",
   :name "insert-left"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L174",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/insert-right",
   :namespace "clojure.zip",
   :line 174,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc item]),
   :doc
   "Inserts the item as the right sibling of the node at this loc,\nwithout moving",
   :name "insert-right"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L150",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/left",
   :namespace "clojure.zip",
   :line 150,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc]),
   :doc
   "Returns the loc of the left sibling of the node at this loc, or nil",
   :name "left"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L157",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/leftmost",
   :namespace "clojure.zip",
   :line 157,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc]),
   :doc
   "Returns the loc of the leftmost sibling of the node at this loc, or self",
   :name "leftmost"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L87",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/lefts",
   :namespace "clojure.zip",
   :line 87,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc]),
   :doc "Returns a seq of the left siblings of this loc",
   :name "lefts"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L76",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/make-node",
   :namespace "clojure.zip",
   :line 76,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc node children]),
   :doc
   "Returns a new branch node, given an existing node and new\nchildren. The loc is only used to supply the constructor.",
   :name "make-node"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L206",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/next",
   :namespace "clojure.zip",
   :line 206,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc]),
   :doc
   "Moves to the next loc in the hierarchy, depth-first. When reaching\nthe end, returns a distinguished loc detectable via end?. If already\nat the end, stays there.",
   :name "next"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L60",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/node",
   :namespace "clojure.zip",
   :line 60,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc]),
   :doc "Returns the node at loc",
   :name "node"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L82",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/path",
   :namespace "clojure.zip",
   :line 82,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc]),
   :doc "Returns a seq of nodes leading to this loc",
   :name "path"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L221",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/prev",
   :namespace "clojure.zip",
   :line 221,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc]),
   :doc
   "Moves to the previous loc in the hierarchy, depth-first. If already\nat the root, returns nil.",
   :name "prev"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L237",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/remove",
   :namespace "clojure.zip",
   :line 237,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc]),
   :doc
   "Removes the node at loc, returning the loc that would have preceded\nit in a depth-first walk.",
   :name "remove"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L183",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/replace",
   :namespace "clojure.zip",
   :line 183,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc node]),
   :doc "Replaces the node at this loc, without moving",
   :name "replace"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L135",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/right",
   :namespace "clojure.zip",
   :line 135,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc]),
   :doc
   "Returns the loc of the right sibling of the node at this loc, or nil",
   :name "right"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L142",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/rightmost",
   :namespace "clojure.zip",
   :line 142,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc]),
   :doc
   "Returns the loc of the rightmost sibling of the node at this loc, or self",
   :name "rightmost"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L92",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/rights",
   :namespace "clojure.zip",
   :line 92,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc]),
   :doc "Returns a seq of the right siblings of this loc",
   :name "rights"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L124",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/root",
   :namespace "clojure.zip",
   :line 124,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc]),
   :doc
   "zips all the way up and returns the root node, reflecting any\nchanges.",
   :name "root"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L34",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/seq-zip",
   :namespace "clojure.zip",
   :line 34,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([root]),
   :doc "Returns a zipper for nested sequences, given a root sequence",
   :name "seq-zip"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L111",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/up",
   :namespace "clojure.zip",
   :line 111,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([loc]),
   :doc
   "Returns the loc of the parent of the node at this loc, or nil if at\nthe top",
   :name "up"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L42",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/vector-zip",
   :namespace "clojure.zip",
   :line 42,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([root]),
   :doc "Returns a zipper for nested vectors, given a root vector",
   :name "vector-zip"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L50",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/xml-zip",
   :namespace "clojure.zip",
   :line 50,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([root]),
   :doc
   "Returns a zipper for xml elements (as from xml/parse),\ngiven a root element",
   :name "xml-zip"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/zip.clj#L18",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.zip-api.html#clojure.zip/zipper",
   :namespace "clojure.zip",
   :line 18,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/zip.clj",
   :var-type "function",
   :arglists ([branch? children make-node root]),
   :doc
   "Creates a new zipper structure. \n\nbranch? is a fn that, given a node, returns true if can have\nchildren, even if it currently doesn't.\n\nchildren is a fn that, given a branch node, returns a seq of its\nchildren.\n\nmake-node is a fn that, given an existing node and a seq of\nchildren, returns a new branch node with the supplied children.\nroot is the root node.",
   :name "zipper"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/test/junit.clj#L182",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test.junit/with-junit-output",
   :namespace "clojure.test.junit",
   :line 182,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/test/junit.clj",
   :var-type "macro",
   :arglists ([& body]),
   :doc
   "Execute body with modified test-is reporting functions that write\nJUnit-compatible XML output.",
   :name "with-junit-output"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/test/tap.clj#L50",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test.tap/print-tap-diagnostic",
   :namespace "clojure.test.tap",
   :line 50,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/test/tap.clj",
   :var-type "function",
   :arglists ([data]),
   :doc
   "Prints a TAP diagnostic line.  data is a (possibly multi-line)\nstring.",
   :name "print-tap-diagnostic"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/test/tap.clj#L62",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test.tap/print-tap-fail",
   :namespace "clojure.test.tap",
   :line 62,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/test/tap.clj",
   :var-type "function",
   :arglists ([msg]),
   :doc
   "Prints a TAP 'not ok' line.  msg is a string, with no line breaks",
   :name "print-tap-fail"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/test/tap.clj#L57",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test.tap/print-tap-pass",
   :namespace "clojure.test.tap",
   :line 57,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/test/tap.clj",
   :var-type "function",
   :arglists ([msg]),
   :doc
   "Prints a TAP 'ok' line.  msg is a string, with no line breaks",
   :name "print-tap-pass"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/test/tap.clj#L45",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test.tap/print-tap-plan",
   :namespace "clojure.test.tap",
   :line 45,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/test/tap.clj",
   :var-type "function",
   :arglists ([n]),
   :doc
   "Prints a TAP plan line like '1..n'.  n is the number of tests",
   :name "print-tap-plan"}
  {:source-url
   "http://github.com/clojure/clojure/blob/76e7c4317dc3eac80c4908ac5e5fb885e302b2a4/src/clj/clojure/test/tap.clj#L106",
   :wiki-url
   "http://clojure.github.com/clojure//clojure.test-api.html#clojure.test.tap/with-tap-output",
   :namespace "clojure.test.tap",
   :line 106,
   :file
   "/home/tom/src/clj/autodoc-stable/../autodoc-work-area/clojure/src/src/clj/clojure/test/tap.clj",
   :var-type "macro",
   :arglists ([& body]),
   :doc
   "Execute body with modified test reporting functions that produce\nTAP output",
   :name "with-tap-output"})}