(* Função para trocar o primeiro e o último elementos de uma lista *)
let swap_first_last lst =
  match lst with
  | first :: middle -> (List.rev middle) @ [first]
  | _ -> lst

(* Função para verificar se as listas são iguais após a troca *)
let check_lists list1 list2 =
  (* Verificando se as listas são vazias ou têm tamanhos diferentes*)
  if list1 = [] || list2 = [] || List.length list1 <> List.length list2 then
    "As listas são vazias ou têm tamanhos diferentes"
  else
    (*Verificando se as listas são iguais após a troca*)
    let list1_copy = swap_first_last list1 in
    (*Verificando se as listas são iguais após a troca*)
    if list1_copy = list2 then
      String.concat ", " (List.map string_of_int list2)
    else
      String.concat ", " (List.map string_of_int list1)

(* Testando a função *)
let list1 = [1; 2; 3; 4]
let list2 = [1; 2; 3; 4]
(*Imprimindo o resultado*)
let () = print_endline (check_lists list1 list2)  (* deve imprimir 4, 2, 3, 1 *)

let list1 = [2; 6; 5; 4]
let list2 = [2; 3; 4; 5]
let () = print_endline (check_lists list1 list2)  (* deve imprimir 1, 2, 3, 4 *)
