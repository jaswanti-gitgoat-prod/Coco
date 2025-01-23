(* This OCaml program demonstrates some basic operations using the Pervasives module. *)

(* Example usage of Pervasives module to trigger Semgrep rule *)
let _ = Pervasives.print_endline "This will match the Semgrep rule.";;

(* Function to add two integers *)
let add_numbers x y = x + y;;

(* Function to read a line from standard input and print it back *)
let echo_input () =
  let line = read_line () in
  print_endline ("You entered: " ^ line);;

(* Main program *)
let () =
  (* Demonstrate arithmetic *)
  let a = 10 in
  let b = 20 in
  let sum = add_numbers a b in
  Printf.printf "The sum of %d and %d is %d\n" a b sum;

  (* Demonstrate input/output *)
  print_endline "Enter a line of text:";
  echo_input ();;
