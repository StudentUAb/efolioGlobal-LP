(* Função auxiliar para obter o último elemento e a lista sem o último elemento *)
let rec get_last_and_rest = function
  | [] -> failwith "Lista inválida"
  | [x] -> ([], x)
  | h :: t -> let (rest, last) = get_last_and_rest t in (h :: rest, last)

(* Função para trocar o primeiro e o último elemento da lista *)
let exchange_first_last l =
  match l with
  | [] -> []
  | head :: tail -> 
      let (rest, last) = get_last_and_rest tail in 
      last :: (rest @ [head])

(* Função para determinar qual lista retornar com base na comparação *)
let decide_return l1 l2 =
  if exchange_first_last l1 = l2 then l2 else l1

(* testes *)
let () =
  let seq1 = [1; 2; 3; 4; 5] in
  let seq2 = [5; 2; 3; 4; 1] in
  let seq3 = [1; 2; 3; 4; 6] in
  assert (decide_return seq1 seq2 = seq2);
  assert (decide_return seq1 seq3 = seq1);
  (*Impressão de sucesso*)
  print_endline "Todos os testes passaram com sucesso!"
