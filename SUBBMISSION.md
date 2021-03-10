# Submission answers
>Please fill in the below as part of your submission:

#####1. How long did the challenge take you to do?

It took me longer than I firstly estimated, rather than following a callback/Rx/Background Thread approach, I thought it would be good to use Coroutines. I've needed 7-8 hours

#####2. What performance considerations have you made?

Well, the main performance consideration was to don't block the thread when making the request, As mentioned, there are existing solutions to handle/mitigate that but I think giving a go to coroutines could be good.

#####3. What design decisions have you made for the public interface?

I'm aware than using Coroutines might have an impact on SDK users, particularly when the public interface is a suspended function that forces the user to call it from a coroutine, I think we all become familiar with
it soon when coroutines are used as a standard for async communication. I guess we could wrap all suspended function that inside the SDKManager to avoid the user the pain (if any?) of calling it from a coroutine so users can communicate
with the SDK through the wrapper in a more simple way. I haven't gone so far but it would be interesting in order to expose a more simple interaction. One of the things I considered is to return an object - PermutiveUserData- which gives
some valuable information for the users, such as whether the results are from cache or not, date, successful response and of course the list of providers. That info can help the users to apply some valuable logic.

#####4. What assumptions have you made on how the SDK is going to be used?

Well. I've assumed the SDK should be singleton, based on the fact the user-id is always the same. The solution provided is probably wrong based on the fact the userID is always the same so there shouldn't be the need to provide it in all calls.
Probably exposing an initialisator where the userID can be set once would be a good idea, and make it not singleton is probably needed, not sure how Permutive users use it at all. I haven't gone in that direction but probably it's the right way to do it.

#####5. What would you do differently if you had more time?

Yeah, I had probably provided different approaches, one based on Rx and another with coroutines, Also exposing a setter/initialisator where the userID is set only once, rather in all calls would be very beneficial.

#####6. What to you makes a well designed SDK?

Well many considerations here, as well as considered it robust(don't crash), effective (it makes what is supposed to do) and efficient ( makes it quickly) a well designed SDK is:
- Simple, intuitive - Simple is always better. That could be applied to everything: set-up, general usage, documentation.
- Clean - If it can be easily understandable, that is probably clean enough.
- Helpful error messages  - We all been in that situation when a error code/message does not make sense at all, so let's save our users time/energy with better error messages.
- Easily approachable - Finding a good balance about exposing functionality but don't overload it with too many methods.
- Lightweight - All test stuff shouldn't be packed. The project should be structured in that way that Jenkins or any other CI product should be pack only production code.
- Multiflavoured - Defining PRO, DEBUG, TESTING flavours and pack each things accordingly the build we need.
- Tracked - Crash report where we can confirm robustness. If it crashes, we need to know it before our users.
- Good maintained - We need to listen our users in order to improve the SDK from their experience.
