% Predicado para trocar o primeiro e último elementos da lista
swap_first_last([H|T], L) :-
    % Verifica se a lista tem mais de um elemento
    reverse(T, [Last|RestRev]),
    % Inverte a lista sem o último elemento
    reverse(RestRev, Rest),
    % Concatena o último elemento com a lista invertida
    append([Last|Rest], [H], L).

% Predicado para verificar se duas listas são iguais
equal_list(L1, L2) :-
    % Verifica se as listas são iguais
    swap_first_last(L1, Swapped),
    % Verifica se as listas são iguais
    Swapped == L2.

% Predicado para decidir qual lista retornar
decide_return(L1, L2, R) :-
    % Verifica se as listas são iguais
    (   equal_list(L1, L2)
    ->  R = L2
    ;   R = L1).

% Testes
?- decide_return([1,2,3,4,5], [5,2,3,4,1], R), write(R), nl.
?- decide_return([1,2,3,4,5], [1,2,3,4,6], R), write(R), nl.
